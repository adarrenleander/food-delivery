package com.soa.fooddelivery.order.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.fooddelivery.order.dto.OrderDto;
import com.soa.fooddelivery.order.service.OrderService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderConsumer {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderConsumer.class);
    @Value("${mq.topic.update-order-status}") private String mqTopicUpdateOrderStatus;
    @Autowired
    OrderService orderService;

    @JmsListener(destination = "${mq.topic.update-order-status}")
    public void updateOrderStatus(String message) {
        ObjectMapper mapper = new ObjectMapper();
        OrderDto orderDto;
        try {
            orderDto = mapper.readValue(message, OrderDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        orderService.updateOrderStatus(orderDto);
    }
}
