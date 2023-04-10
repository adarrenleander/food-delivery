package com.soa.fooddelivery.payment.service;

import com.soa.fooddelivery.payment.dto.RefundDto;
import com.soa.fooddelivery.payment.dto.TransactionStatusDto;
import com.soa.fooddelivery.payment.entity.Transaction;
import com.soa.fooddelivery.payment.repository.TransactionRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RefundService.class);
    @Autowired private TransactionRepository transactionRepository;

    public TransactionStatusDto refundPayment(RefundDto refund) {
        TransactionStatusDto res = new TransactionStatusDto();

        Transaction payment = transactionRepository.findByIdAndType(refund.getTransactionId(), "payment");
        if (payment == null) {
            res.setTransactionId(refund.getTransactionId());
            res.setStatus("invalid");
            return res;
        }

        Float totalRefunded = 0F;
        for (Transaction t : transactionRepository.findByOrderIdAndType(refund.getTransactionId(), "refund")) {
            totalRefunded += t.getTotalAmount();
        }

        if (totalRefunded + refund.getRefundAmount() > payment.getTotalAmount()) {
            res.setTransactionId(refund.getTransactionId());
            res.setStatus("invalid");
            return res;
        }

        Transaction transaction = new Transaction();
        transaction.setUserId(refund.getUserId());
        transaction.setOrderId(refund.getTransactionId());
        transaction.setTotalAmount(refund.getRefundAmount());
        transaction.setTransactionType("refund");
        transaction.setStatus("in progress");
        transaction = transactionRepository.save(transaction);

        // the refund process is mocked from third-party provider

        transaction.setStatus("success");
        transaction = transactionRepository.save(transaction);

        return transaction.convertToDto();
    }
}
