package com.soa.fooddelivery.order.service;

import com.soa.fooddelivery.order.dto.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderService.class);
    @Value("${notify.topic.order-placed}") private String notifyTopicOrderPlaced;
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

        notificationService.sendNotification(notifyTopicOrderPlaced, "xxx", request.getUserId());

        response.setStatus("placed");
        return response;
    }
}
