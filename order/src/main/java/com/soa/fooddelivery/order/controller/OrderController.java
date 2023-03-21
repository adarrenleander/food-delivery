package com.soa.fooddelivery.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @PostMapping("/order")
    public ResponseEntity<Object> createOrder(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }

    @PutMapping("/order")
    public ResponseEntity<Object> updateOrderStatus(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<Object> getOrdersHistory(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok().body(new Object());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Object> getOrder(@PathVariable(name = "orderId") String orderId) {
        return ResponseEntity.ok().body(new Object());
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Object> cancelOrder(@PathVariable(name = "orderId") String orderId) {
        return ResponseEntity.ok().body(new Object());
    }
}
