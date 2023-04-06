package com.soa.fooddelivery.payment.service;

import com.soa.fooddelivery.payment.dto.TransactionStatusDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TransactionStatusService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TransactionStatusService.class);

    public TransactionStatusDto checkTransactionStatus(TransactionStatusDto trxStatus) {
        // TODO: query transaction from DB

        TransactionStatusDto res = new TransactionStatusDto();
        res.setTransactionId(trxStatus.getTransactionId());
        res.setTransactionType(trxStatus.getTransactionType());
        res.setUserId(trxStatus.getUserId());
        res.setOrderId(trxStatus.getOrderId());
        res.setStatus("success");

        return res;
    }
}
