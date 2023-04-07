package com.soa.fooddelivery.notification.service;

import com.soa.fooddelivery.notification.dto.NotificationTemplateDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class NotificationTemplateService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NotificationTemplateService.class);

    public NotificationTemplateDto createNotificationTemplate(NotificationTemplateDto notifTemplate) {
        // TODO: save notification template to DB

        NotificationTemplateDto res = new NotificationTemplateDto();
        res.setNotificationId("xxx");
        res.setTitle(notifTemplate.getTitle());
        res.setMessage(notifTemplate.getMessage());
        res.setCategory(notifTemplate.getCategory());
        res.setActive(true);
        return res;
    }

    public NotificationTemplateDto updateNotificationTemplate(NotificationTemplateDto notifTemplate) {
        // TODO: update notification template to DB

        NotificationTemplateDto res = new NotificationTemplateDto();
        res.setNotificationId(notifTemplate.getNotificationId());
        res.setTitle(notifTemplate.getTitle());
        res.setMessage(notifTemplate.getMessage());
        res.setCategory(notifTemplate.getCategory());
        res.setActive(notifTemplate.getActive());
        return res;
    }

    public NotificationTemplateDto deleteNotificationTemplate(String notificationId) {
        // TODO: set active false in DB

        NotificationTemplateDto res = new NotificationTemplateDto();
        res.setNotificationId(notificationId);
        res.setActive(false);
        return res;
    }

    public NotificationTemplateDto[] getNotificationTemplatesList() {
        // TODO: query all notification templates from DB

        NotificationTemplateDto notificationTemplate = getDummyNotificationTemplate();
        return new NotificationTemplateDto[]{notificationTemplate, notificationTemplate, notificationTemplate};
    }

    public NotificationTemplateDto getNotificationTemplate(String notificationId) {
        // TODO: query single notification template

        NotificationTemplateDto res = getDummyNotificationTemplate();
        res.setNotificationId(notificationId);
        return res;
    }

    public NotificationTemplateDto getDummyNotificationTemplate() {
        NotificationTemplateDto notificationTemplate = new NotificationTemplateDto();
        notificationTemplate.setNotificationId("xxx");
        notificationTemplate.setTitle("title");
        notificationTemplate.setMessage("message");
        notificationTemplate.setCategory("category");
        notificationTemplate.setActive(true);
        return notificationTemplate;
    }
}
