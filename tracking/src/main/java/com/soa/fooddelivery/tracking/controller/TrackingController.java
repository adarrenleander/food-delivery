package com.soa.fooddelivery.tracking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackingController {
    @GetMapping("/order/{orderId}/status")
    public ResponseEntity<Object> getOrderStatus(@PathVariable(name = "orderId") String orderId) {
        return ResponseEntity.ok().body(new Object());
    }

    @GetMapping("/order/{orderId}/track")
    public ResponseEntity<Object> getOrderTracking(@PathVariable(name = "orderId") String orderId) {
        return ResponseEntity.ok().body(new Object());
    }
}
