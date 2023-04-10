package com.soa.fooddelivery.notification.service;

import com.soa.fooddelivery.notification.dto.NotificationDto;
import com.soa.fooddelivery.notification.entity.Notification;
import com.soa.fooddelivery.notification.entity.NotificationTemplate;
import com.soa.fooddelivery.notification.repository.NotificationRepository;
import com.soa.fooddelivery.notification.repository.NotificationTemplateRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NotificationService.class);
    @Autowired private NotificationRepository notificationRepository;
    @Autowired private NotificationTemplateRepository notificationTemplateRepository;

    public NotificationDto sendNotification(NotificationDto notificationDto) {
        notificationDto.setStatus("failed");

        List<NotificationTemplate> list = notificationTemplateRepository.findAllById(notificationDto.getNotificationTemplateId());
        if (list.isEmpty()) {
            return notificationDto;
        }

        NotificationTemplate notificationTemplate = list.get(0);
        if (notificationTemplate.getActive()) {
            Notification notification = new Notification();
            notification.setUserId(notificationDto.getUserId());
            notification.setStatus("sent");
            notification.setNotificationTemplate(notificationTemplate);
            notificationRepository.save(notification);
            notificationDto.setStatus("success");
        }

        return notificationDto;
    }
}
