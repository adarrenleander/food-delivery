package com.soa.fooddelivery.order.controller;

import com.soa.fooddelivery.order.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired private JmsTemplate jmsTemplate;
    @Autowired private RestTemplateBuilder restTemplateBuilder;

    @PostMapping("/order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto request) {
        NotifyDto pubReq = new NotifyDto();
        pubReq.setUserId(request.getUserId());
        pubReq.setNotificationId("xxx");
        jmsTemplate.convertAndSend("notify-order-placed", pubReq);

        RestTemplate restTemplate = restTemplateBuilder.build();
        PaymentRequestDto req = new PaymentRequestDto();
        req.setOrderId("xxx");
        req.setUserId(request.getUserId());
        req.setTotalAmount(request.getTotalAmount());
        PaymentResponseDto res = restTemplate.postForObject("http://localhost:8084/pay", req, PaymentResponseDto.class); // this should change to payment
        System.out.println(res.getTransactionId());
        System.out.println(res.getStatus());

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
