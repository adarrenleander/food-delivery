package com.soa.fooddelivery.payment.service;

import com.soa.fooddelivery.payment.dto.PaymentDto;
import com.soa.fooddelivery.payment.dto.TransactionStatusDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PaymentService.class);

    public TransactionStatusDto chargePayment(PaymentDto payment) {
        // TODO: save payment to DB

        // the payment should be handled by a third-party provider,
        // but we will not implement that in this scope,
        // so we will make a mock success payment
        TransactionStatusDto res = new TransactionStatusDto();
        res.setTransactionId("xxx");
        res.setStatus("success");
        return res;
    }
}
