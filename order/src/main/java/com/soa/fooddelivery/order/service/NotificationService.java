package com.soa.fooddelivery.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.fooddelivery.order.dto.NotifyDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NotificationService.class);
    @Autowired private JmsTemplate jmsTemplate;
    @Value("${mq.topic.send-notification}") private String mqTopicSendNotification;

    public void sendNotification(Integer notificationId, Integer userId) {
        NotifyDto pubReq = new NotifyDto();
        pubReq.setUserId(userId);
        pubReq.setNotificationId(notificationId);

        ObjectMapper mapper = new ObjectMapper();
        try {
            jmsTemplate.convertAndSend(mqTopicSendNotification, mapper.writeValueAsString(pubReq));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed convert to JSON string in " + mqTopicSendNotification);
        }
    }
}
