package com.soa.fooddelivery.notification.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.fooddelivery.notification.dto.NotificationDto;
import com.soa.fooddelivery.notification.entity.Notification;
import com.soa.fooddelivery.notification.entity.NotificationTemplate;
import com.soa.fooddelivery.notification.repository.NotificationRepository;
import com.soa.fooddelivery.notification.repository.NotificationTemplateRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NotificationConsumer {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NotificationConsumer.class);
    @Value("${mq.topic.send-notification}") private String mqTopicSendNotification;
    @Autowired private NotificationRepository notificationRepository;
    @Autowired private NotificationTemplateRepository notificationTemplateRepository;

    @JmsListener(destination = "${mq.topic.send-notification}")
    public void receiveOrderPlaced(String message) {
        ObjectMapper mapper = new ObjectMapper();
        NotificationDto notificationDto;
        try {
            notificationDto = mapper.readValue(message, NotificationDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Consumer \"" + mqTopicSendNotification + "\": Notification " + notificationDto.getNotificationTemplateId() + " sent for User " + notificationDto.getUserId());

        NotificationTemplate notificationTemplate = notificationTemplateRepository.findAllById(notificationDto.getNotificationTemplateId()).get(0);

        if (notificationTemplate.getActive()) {
            Notification notification = new Notification();
            notification.setUserId(notificationDto.getUserId());
            notification.setStatus("sent");
            notification.setNotificationTemplate(notificationTemplate);
            notificationRepository.save(notification);
        }
    }
}
