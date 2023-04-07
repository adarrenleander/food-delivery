package com.soa.fooddelivery.notification.controller;

import com.soa.fooddelivery.notification.dto.NotificationDto;
import com.soa.fooddelivery.notification.dto.NotificationTemplateDto;
import com.soa.fooddelivery.notification.service.NotificationService;
import com.soa.fooddelivery.notification.service.NotificationTemplateService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NotificationController.class);
    @Autowired private NotificationService notificationService;
    @Autowired private NotificationTemplateService notificationTemplateService;

    @PostMapping("/notification/template")
    public ResponseEntity<NotificationTemplateDto> createNotificationTemplate(@RequestBody NotificationTemplateDto request) {
        log.debug("POST /notification/template createNotificationTemplate");
        NotificationTemplateDto response = notificationTemplateService.createNotificationTemplate(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/notification/template")
    public ResponseEntity<NotificationTemplateDto> updateNotificationTemplate(@RequestBody NotificationTemplateDto request) {
        log.debug("PUT /notification/template updateNotificationTemplate");
        NotificationTemplateDto response = notificationTemplateService.updateNotificationTemplate(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/notification/template/{notificationId}")
    public ResponseEntity<NotificationTemplateDto> deleteNotificationTemplate(@PathVariable(name = "notificationId") String notificationId) {
        log.debug("DELETE /notification/template/{notificationId} deleteNotificationTemplate");
        NotificationTemplateDto response = notificationTemplateService.deleteNotificationTemplate(notificationId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/notification/templates")
    public ResponseEntity<NotificationTemplateDto[]> getNotificationTemplatesList() {
        log.debug("GET /notification/templates getNotificationTemplatesList");
        NotificationTemplateDto[] response = notificationTemplateService.getNotificationTemplatesList();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/notification/template/{notificationId}")
    public ResponseEntity<NotificationTemplateDto> getNotificationTemplate(@PathVariable(name = "notificationId") String notificationId) {
        log.debug("GET /notification/template/{notificationId}");
        NotificationTemplateDto response = notificationTemplateService.getNotificationTemplate(notificationId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/notification/send")
    public ResponseEntity<NotificationDto> sendNotification(@RequestBody NotificationDto request) {
        log.debug("POST /notification/send sendNotification");
        NotificationDto response = notificationService.sendNotification(request);
        return ResponseEntity.ok().body(response);
    }


}
