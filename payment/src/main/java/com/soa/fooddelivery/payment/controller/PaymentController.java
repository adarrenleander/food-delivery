package com.soa.fooddelivery.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @PostMapping("/pay")
    public ResponseEntity<Object> chargePayment(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }

    @PutMapping("/payment/status")
    public ResponseEntity<Object> updatePaymentStatus(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }

    @PostMapping("/refund")
    public ResponseEntity<Object> refundPayment(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }
}
