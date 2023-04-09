package com.soa.fooddelivery.order.service;

import com.soa.fooddelivery.order.dto.*;
import com.soa.fooddelivery.order.entity.Delivery;
import com.soa.fooddelivery.order.entity.Order;
import com.soa.fooddelivery.order.entity.OrderItem;
import com.soa.fooddelivery.order.repository.DeliveryRepository;
import com.soa.fooddelivery.order.repository.OrderItemRepository;
import com.soa.fooddelivery.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class OrderService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderService.class);
    @Autowired private PromotionService promotionService;
    @Autowired private PaymentService paymentService;
    @Autowired private DispatchService dispatchService;
    @Autowired private NotificationService notificationService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private DeliveryRepository deliveryRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    public OrderDto createOrder(OrderDto request) {
        OrderDto response = new OrderDto();
        PromotionEligibilityDto resPromoEligible = null;

        if (!request.getPromotionCode().isEmpty()) {
            resPromoEligible = promotionService.checkPromotionEligiblity(request);
            if (resPromoEligible.getEligibility()) {
                request.setTotalAmount(resPromoEligible.getFinalAmount());
            }
        }

        Order order = saveOrderToDB(request);
        response.setOrderId(order.getId());

        TransactionStatusDto resPay = paymentService.chargePayment(request);
        if (resPay.getStatus().equals("failed")) {
            updateOrderStatus(new OrderDto(order.getId(), "failed"));
            response.setStatus("failed");
            return response;
        }

        if (resPromoEligible != null && resPromoEligible.getEligibility()) {
            PromotionUserDto resPromoApply = promotionService.applyPromotion(request);
            if (!resPromoApply.getUsageStatus().equals("success")) {
                paymentService.refundPayment(resPay.getTransactionId(), request.getUserId(), request.getTotalAmount());
                updateOrderStatus(new OrderDto(order.getId(), "failed"));
                response.setStatus("failed");
                return response;
            }
        }

        DispatchDto resDispatch = dispatchService.dispatchOrder(request);
        if (!resDispatch.getStatus().equals("created")) {
            paymentService.refundPayment(resPay.getTransactionId(), request.getUserId(), request.getTotalAmount());
            updateOrderStatus(new OrderDto(order.getId(), "failed"));
            response.setStatus("failed");
            return response;
        }

        updateOrderStatus(new OrderDto(order.getId(), "placed"));
        response.setStatus("placed");

        notificationService.sendNotification(1, request.getUserId());

        return response;
    }

    @Transactional(rollbackFor={Exception.class})
    public Order saveOrderToDB(OrderDto request) {
        Delivery delivery = new Delivery();
        delivery.setName(request.getDelivery().getName());
        delivery.setAddress(request.getDelivery().getAddress());
        delivery.setPhoneNumber(request.getDelivery().getPhoneNumber());
        delivery.setTime(request.getDelivery().getTime());
        deliveryRepository.save(delivery);

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setRestaurantId(request.getRestaurantId());
        order.setPromotionCode(request.getPromotionCode());
        order.setTotalAmount(request.getTotalAmount());
        order.setDelivery(delivery);
        order.setStatus("created");
        order = orderRepository.save(order);

        for (OrderItemDto oi : request.getOrders()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItemId(oi.getMenuItemId());
            orderItem.setQuantity(oi.getQuantity());
            orderItem.setNotes(oi.getNotes());
            orderItem.setPrice(oi.getPrice());
            orderItemRepository.save(orderItem);
        }

        return order;
    }

    @Transactional(rollbackFor={Exception.class})
    public OrderDto updateOrderStatus(OrderDto request) {
        Order order = orderRepository.findAllById(request.getOrderId()).get(0);
        order.setStatus(request.getStatus());
        orderRepository.save(order);

        OrderDto response = new OrderDto();
        response.setOrderId(order.getId());
        response.setStatus(order.getStatus());
        return response;
    }

    public ArrayList<OrderDto> getOrdersHistory(Integer userId) {
        ArrayList<OrderDto> list = new ArrayList<>();

        for (Order order : orderRepository.findAllByUserId(userId)) {
            OrderDto orderDto = order.convertToDto();
            orderDto.setOrders(orderItemRepository.findAllByOrderId(order.getId()));
            list.add(orderDto);
        }
        return list;
    }

    public OrderDto getOrder(Integer orderId) {
        Order order = orderRepository.findAllById(orderId).get(0);
        OrderDto response = order.convertToDto();
        response.setOrders(orderItemRepository.findAllByOrderId(order.getId()));
        return response;
    }

    @Transactional(rollbackFor={Exception.class})
    public OrderDto cancelOrder(Integer orderId) {
        return updateOrderStatus(new OrderDto(orderId, "canceled"));
    }
}
