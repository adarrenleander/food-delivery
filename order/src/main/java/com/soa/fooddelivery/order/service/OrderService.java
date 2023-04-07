package com.soa.fooddelivery.order.service;

import com.soa.fooddelivery.order.dto.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderService.class);
    @Value("${mq.topic.order-placed}") private String mqTopicOrderPlaced;
    @Autowired private PromotionService promotionService;
    @Autowired private PaymentService paymentService;
    @Autowired private DispatchService dispatchService;
    @Autowired private NotificationService notificationService;

    public OrderDto createOrder(OrderDto request) {
        OrderDto response = new OrderDto();

        PromotionEligibilityDto resPromoEligible = promotionService.checkPromotionEligiblity(request);
        if (resPromoEligible.getEligibility()) {
            request.setTotalAmount(resPromoEligible.getFinalAmount());
        }

        // TODO: save order to DB with status created
        response.setOrderId("xxx");

        TransactionStatusDto resPay = paymentService.chargePayment(request);
        if (resPay.getStatus().equals("failed")) {
            response.setStatus("failed");
            // TODO: update order to failed in DB
            return response;
        }

        // should failed apply promo lead to failed order?
        PromotionUserDto resPromoApply = promotionService.applyPromotion(request);
        if (!resPromoApply.getUsageStatus().equals("success")) {
            TransactionStatusDto resRefund = paymentService.refundPayment(resPay.getTransactionId(), request.getUserId(), request.getTotalAmount());

            // TODO: update order to failed in DB

            response.setStatus("failed");
            return response;
        }

        DispatchDto resDispatch = dispatchService.dispatchOrder(request);
        if (!resDispatch.getStatus().equals("created")) {
            TransactionStatusDto resRefund = paymentService.refundPayment(resPay.getTransactionId(), request.getUserId(), request.getTotalAmount());

            // TODO: update order to failed in DB

            response.setStatus("failed");
            return response;
        }

        // TODO: update order status to placed

        notificationService.sendNotification(mqTopicOrderPlaced, "xxx", request.getUserId());

        response.setStatus("placed");
        return response;
    }

    public OrderDto updateOrderStatus(OrderDto request) {
        // TODO: update the order status in DB according to status and orderId in request

        OrderDto response = new OrderDto();
        response.setOrderId(request.getOrderId());
        response.setStatus("completed");
        return response;
    }

    public OrderDto[] getOrdersHistory(String userId) {
        // TODO: query all orders from DB descending by date

        OrderDto order = getDummyFullOrder();
        order.setUserId(userId);
        return new OrderDto[]{order, order, order};
    }

    public OrderDto getOrder(String orderId) {
        // TODO: query order by orderId to DB

        OrderDto response = getDummyFullOrder();
        response.setOrderId(orderId);
        return response;
    }

    public OrderDto cancelOrder(String orderId) {
        // TODO: set order status to canceled in DB

        OrderDto response = new OrderDto();
        response.setOrderId(orderId);
        response.setStatus("canceled");
        return response;
    }

    public OrderDto getDummyFullOrder() {
        DeliveryDto delivery = new DeliveryDto();
        delivery.setName("name");
        delivery.setPhoneNumber("+31123456");
        delivery.setTime("2023-03-22 15:30");
        delivery.setAddress("Drienerlolaan 5, 7522 NB Enschede");

        OrderItemDto order1 = new OrderItemDto();
        order1.setMenuItemId("xxx");
        order1.setPrice(5F);
        order1.setQuantity(1);
        order1.setNotes("");

        OrderItemDto order2 = new OrderItemDto();
        order2.setMenuItemId("xxx");
        order2.setPrice(10F);
        order2.setQuantity(1);
        order2.setNotes("");

        OrderItemDto[] orders = {order1, order2};

        OrderDto order = new OrderDto();
        order.setOrderId("xxx");
        order.setUserId("xxx");
        order.setRestaurantId("xxx");
        order.setStatus("completed");
        order.setTotalAmount(15F);
        order.setOrders(orders);
        order.setDelivery(delivery);
        return order;
    }
}
