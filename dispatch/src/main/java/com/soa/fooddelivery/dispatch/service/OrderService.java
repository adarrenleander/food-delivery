package com.soa.fooddelivery.dispatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.fooddelivery.dispatch.dto.OrderDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${mq.topic.update-order-status}") private String mqTopicUpdateOrderStatus;

    public void updateOrderStatus(Integer orderId, String status) {
        OrderDto pubReq = new OrderDto();
        pubReq.setOrderId(orderId);
        pubReq.setStatus(status);

        ObjectMapper mapper = new ObjectMapper();
        try {
            jmsTemplate.convertAndSend(mqTopicUpdateOrderStatus, mapper.writeValueAsString(pubReq));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed convert to JSON string in " + mqTopicUpdateOrderStatus);
        }
    }
}
