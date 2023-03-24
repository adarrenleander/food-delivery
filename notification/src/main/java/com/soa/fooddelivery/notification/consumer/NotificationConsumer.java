package com.soa.fooddelivery.notification.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.fooddelivery.notification.dto.NotificationDto;
import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NotificationConsumer {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NotificationConsumer.class);

    @JmsListener(destination = "notify-order-placed")
    public void receiveOrderPlaced(String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        NotificationDto notif = mapper.readValue(message, NotificationDto.class);
        log.info("Consumer \"notify-order-placed\": Notification " + notif.getNotificationId() + " sent for User " + notif.getUserId());
    }
}
