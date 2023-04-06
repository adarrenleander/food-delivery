package com.soa.fooddelivery.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.fooddelivery.order.dto.NotifyDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NotificationService.class);
    @Autowired private JmsTemplate jmsTemplate;

    public void sendNotification(String destination, String notificationId, String userId) {
        NotifyDto pubReq = new NotifyDto();
        pubReq.setUserId(userId);
        pubReq.setNotificationId(notificationId);

        ObjectMapper mapper = new ObjectMapper();
        try {
            jmsTemplate.convertAndSend(destination, mapper.writeValueAsString(pubReq));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed convert to JSON string in " + destination);
        }
    }
}
