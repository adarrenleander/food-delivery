package com.soa.fooddelivery.order.controller;

import com.soa.fooddelivery.order.dto.DeliveryDto;
import com.soa.fooddelivery.order.dto.OrderDto;
import com.soa.fooddelivery.order.dto.OrderRequestDto;
import com.soa.fooddelivery.order.dto.OrderResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @PostMapping("/order")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto request) {
        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId("xxx");
        response.setStatus("placed");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@RequestBody OrderRequestDto request) {
        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(request.getOrderId());
        response.setStatus("completed");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<OrderRequestDto[]> getOrdersHistory(@PathVariable(name = "userId") String userId) {
        OrderRequestDto[] response = {getSampleFullOrder(), getSampleFullOrder(), getSampleFullOrder()};
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderRequestDto> getOrder(@PathVariable(name = "orderId") String orderId) {
        OrderRequestDto response = getSampleFullOrder();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable(name = "orderId") String orderId) {
        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(orderId);
        response.setStatus("canceled");
        return ResponseEntity.ok().body(response);
    }

    public OrderRequestDto getSampleFullOrder() {
        DeliveryDto delivery = new DeliveryDto();
        delivery.setName("name");
        delivery.setPhoneNumber("+31123456");
        delivery.setTime("2023-03-22 15:30");
        delivery.setAddress("Drienerlolaan 5, 7522 NB Enschede");

        OrderDto order1 = new OrderDto();
        order1.setMenuItemId("xxx");
        order1.setPrice(5F);
        order1.setQuantity(1);
        order1.setNotes("");

        OrderDto order2 = new OrderDto();
        order2.setMenuItemId("xxx");
        order2.setPrice(10F);
        order2.setQuantity(1);
        order2.setNotes("");

        OrderDto[] orders = {order1, order2};

        OrderRequestDto response = new OrderRequestDto();
        response.setOrderId("xxx");
        response.setUserId("xxx");
        response.setRestaurantId("xxx");
        response.setTotalAmount(15F);
        response.setOrders(orders);
        response.setDelivery(delivery);
        return response;
    }
}
