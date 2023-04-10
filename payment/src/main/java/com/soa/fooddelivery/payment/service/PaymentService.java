package com.soa.fooddelivery.payment.service;

import com.soa.fooddelivery.payment.dto.PaymentDto;
import com.soa.fooddelivery.payment.dto.TransactionStatusDto;
import com.soa.fooddelivery.payment.entity.Transaction;
import com.soa.fooddelivery.payment.repository.TransactionRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PaymentService.class);
    @Autowired private TransactionRepository transactionRepository;

    public TransactionStatusDto chargePayment(PaymentDto payment) {
        Transaction transaction = new Transaction();
        transaction.setUserId(payment.getUserId());
        transaction.setOrderId(payment.getOrderId());
        transaction.setTotalAmount(payment.getTotalAmount());
        transaction.setTransactionType("payment");
        transaction.setStatus("in progress");
        transaction = transactionRepository.save(transaction);

        // the payment should be handled by a third-party provider,
        // but we will not implement that in this scope,
        // so we will make a mock success payment

        transaction.setStatus("success");
        transaction = transactionRepository.save(transaction);

        return transaction.convertToDto();
    }
}
