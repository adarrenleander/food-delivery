package com.soa.fooddelivery.payment.service;

import com.soa.fooddelivery.payment.dto.RefundDto;
import com.soa.fooddelivery.payment.dto.TransactionStatusDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RefundService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RefundService.class);

    public TransactionStatusDto refundPayment(RefundDto refund) {
        // TODO: check in DB if payment exists

        // TODO: save refund to DB

        // the refund is mocked from third-party provider
        TransactionStatusDto res = new TransactionStatusDto();
        res.setTransactionId(1);
        res.setStatus("success");
        return res;
    }
}
