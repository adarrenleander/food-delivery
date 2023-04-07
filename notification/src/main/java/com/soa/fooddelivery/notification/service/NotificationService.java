package com.soa.fooddelivery.notification.service;

import com.soa.fooddelivery.notification.dto.NotificationDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NotificationService.class);

    public NotificationDto sendNotification(NotificationDto notif) {
        // TODO: dummy send notification to clients

        NotificationDto res = new NotificationDto();
        res.setUserId(notif.getUserId());
        res.setNotificationId(notif.getNotificationId());
        res.setStatus("success");
        return res;
    }
}
