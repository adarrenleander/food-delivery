package com.soa.fooddelivery.payment.controller;

import com.soa.fooddelivery.payment.dto.PaymentDto;
import com.soa.fooddelivery.payment.dto.RefundDto;
import com.soa.fooddelivery.payment.dto.TransactionStatusDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @PostMapping("/pay")
    public ResponseEntity<TransactionStatusDto> chargePayment(@RequestBody PaymentDto request) {
        TransactionStatusDto response = new TransactionStatusDto();
        response.setTransactionId("xxx");
        response.setStatus("pending");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/transaction/status")
    public ResponseEntity<TransactionStatusDto> checkTransactionStatus(@RequestBody TransactionStatusDto request) {
        TransactionStatusDto response = new TransactionStatusDto();
        response.setTransactionId(request.getTransactionId());
        response.setTransactionType(request.getTransactionType());
        response.setUserId(request.getUserId());
        response.setOrderId(request.getOrderId());
        response.setStatus("success");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/refund")
    public ResponseEntity<TransactionStatusDto> refundPayment(@RequestBody RefundDto request) {
        TransactionStatusDto response = new TransactionStatusDto();
        response.setTransactionId("xxx");
        response.setStatus("pending");
        return ResponseEntity.ok().body(response);
    }
}
