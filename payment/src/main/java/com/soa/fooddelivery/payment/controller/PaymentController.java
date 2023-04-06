package com.soa.fooddelivery.payment.controller;

import com.soa.fooddelivery.payment.dto.PaymentDto;
import com.soa.fooddelivery.payment.dto.RefundDto;
import com.soa.fooddelivery.payment.dto.TransactionStatusDto;
import com.soa.fooddelivery.payment.service.PaymentService;
import com.soa.fooddelivery.payment.service.RefundService;
import com.soa.fooddelivery.payment.service.TransactionStatusService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PaymentController.class);
    @Autowired private PaymentService paymentService;
    @Autowired private RefundService refundService;
    @Autowired private TransactionStatusService transactionStatusService;

    @PostMapping("/pay")
    public ResponseEntity<TransactionStatusDto> chargePayment(@RequestBody PaymentDto request) {
        log.debug("POST /pay chargePayment");
        TransactionStatusDto response = paymentService.chargePayment(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/transaction/status")
    public ResponseEntity<TransactionStatusDto> checkTransactionStatus(@RequestBody TransactionStatusDto request) {
        log.debug("POST /transaction/status checkTransactionStatus");
        TransactionStatusDto response = transactionStatusService.checkTransactionStatus(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/refund")
    public ResponseEntity<TransactionStatusDto> refundPayment(@RequestBody RefundDto request) {
        log.debug("POST /refund refundPayment");
        TransactionStatusDto response = refundService.refundPayment(request);
        return ResponseEntity.ok().body(response);
    }
}
