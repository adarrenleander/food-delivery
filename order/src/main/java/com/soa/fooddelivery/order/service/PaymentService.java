package com.soa.fooddelivery.order.service;

import com.soa.fooddelivery.order.configuration.PaymentConfiguration;
import com.soa.fooddelivery.order.dto.OrderDto;
import com.soa.fooddelivery.order.dto.PaymentDto;
import com.soa.fooddelivery.order.dto.RefundDto;
import com.soa.fooddelivery.order.dto.TransactionStatusDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PaymentService.class);
    @Autowired private PaymentConfiguration paymentConfiguration;
    @Autowired private RestTemplateBuilder restTemplateBuilder;

    public TransactionStatusDto chargePayment(OrderDto order) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = paymentConfiguration.getHost() + paymentConfiguration.getPayPath();

        PaymentDto req = new PaymentDto();
        req.setOrderId(order.getOrderId());
        req.setUserId(order.getUserId());
        req.setTotalAmount(order.getTotalAmount());

        TransactionStatusDto res = restTemplate.postForObject(url, req, TransactionStatusDto.class);
        log.info("CHARGE PAYMENT order:" + req.getOrderId() + ", transaction:" + res.getTransactionId() + ", status:" + res.getStatus());

        // TODO: handle unhappy flow

        return res;
    }

    public TransactionStatusDto refundPayment(String transactionId, String userId, Float refundAmount) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = paymentConfiguration.getHost() + paymentConfiguration.getRefundPath();

        RefundDto req = new RefundDto();
        req.setTransactionId(transactionId);
        req.setUserId(userId);
        req.setRefundAmount(refundAmount);

        TransactionStatusDto res = restTemplate.postForObject(url, req, TransactionStatusDto.class);
        log.info("REFUND PAYMENT transaction:" + req.getTransactionId() + ", user:" + req.getUserId() + ", refundAmount:" + req.getRefundAmount());

        // TODO: handle unhappy flow

        return res;
    }
}
