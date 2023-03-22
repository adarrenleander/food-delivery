package com.soa.fooddelivery.order.controller;

import com.soa.fooddelivery.order.dto.DeliveryDto;
import com.soa.fooddelivery.order.dto.OrderDto;
import com.soa.fooddelivery.order.dto.OrderItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @PostMapping("/order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto request) {
        OrderDto response = new OrderDto();
        response.setOrderId("xxx");
        response.setStatus("placed");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderDto> updateOrderStatus(@RequestBody OrderDto request) {
        OrderDto response = new OrderDto();
        response.setOrderId(request.getOrderId());
        response.setStatus("completed");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<OrderDto[]> getOrdersHistory(@PathVariable(name = "userId") String userId) {
        OrderDto order = getDummyFullOrder();
        order.setUserId(userId);
        OrderDto[] response = {order, order, order};
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable(name = "orderId") String orderId) {
        OrderDto response = getDummyFullOrder();
        response.setOrderId(orderId);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable(name = "orderId") String orderId) {
        OrderDto response = new OrderDto();
        response.setOrderId(orderId);
        response.setStatus("canceled");
        return ResponseEntity.ok().body(response);
    }

    public OrderDto getDummyFullOrder() {
        DeliveryDto delivery = new DeliveryDto();
        delivery.setName("name");
        delivery.setPhoneNumber("+31123456");
        delivery.setTime("2023-03-22 15:30");
        delivery.setAddress("Drienerlolaan 5, 7522 NB Enschede");

        OrderItemDto order1 = new OrderItemDto();
        order1.setMenuItemId("xxx");
        order1.setPrice(5F);
        order1.setQuantity(1);
        order1.setNotes("");

        OrderItemDto order2 = new OrderItemDto();
        order2.setMenuItemId("xxx");
        order2.setPrice(10F);
        order2.setQuantity(1);
        order2.setNotes("");

        OrderItemDto[] orders = {order1, order2};

        OrderDto order = new OrderDto();
        order.setOrderId("xxx");
        order.setUserId("xxx");
        order.setRestaurantId("xxx");
        order.setStatus("completed");
        order.setTotalAmount(15F);
        order.setOrders(orders);
        order.setDelivery(delivery);
        return order;
    }
}
