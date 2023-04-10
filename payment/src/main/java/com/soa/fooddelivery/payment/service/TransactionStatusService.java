package com.soa.fooddelivery.payment.service;

import com.soa.fooddelivery.payment.dto.TransactionStatusDto;
import com.soa.fooddelivery.payment.entity.Transaction;
import com.soa.fooddelivery.payment.repository.TransactionRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionStatusService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TransactionStatusService.class);
    @Autowired private TransactionRepository transactionRepository;

    public TransactionStatusDto checkTransactionStatus(TransactionStatusDto trxStatus) {
        TransactionStatusDto res = new TransactionStatusDto();

        List<Transaction> transactionList = transactionRepository.findAllById(trxStatus.getTransactionId());

        if (transactionList.isEmpty()) {
            res.setTransactionId(trxStatus.getTransactionId());
            res.setStatus("invalid");
            return res;
        }

        return transactionList.get(0).convertToDto();
    }
}
