package com.soa.fooddelivery.order.controller;

import com.soa.fooddelivery.order.dto.*;
import com.soa.fooddelivery.order.service.OrderService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class OrderController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderController.class);
    @Autowired private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto request) {
        log.debug("POST /order createOrder");
        OrderDto response = orderService.createOrder(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderDto> updateOrderStatus(@RequestBody OrderDto request) {
        log.debug("PUT /order updateOrderStatus");
        OrderDto response = orderService.updateOrderStatus(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<ArrayList<OrderDto>> getOrdersHistory(@PathVariable(name = "userId") Integer userId) {
        log.debug("GET /orders/{userId} getOrdersHistory");
        ArrayList<OrderDto> response = orderService.getOrdersHistory(userId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable(name = "orderId") Integer orderId) {
        log.debug("GET /order/{orderId} getOrder");
        OrderDto response = orderService.getOrder(orderId);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable(name = "orderId") Integer orderId) {
        log.debug("DELETE /order/{orderId} cancelOrder");
        OrderDto response = orderService.cancelOrder(orderId);
        return ResponseEntity.ok().body(response);
    }
}
