package com.soa.fooddelivery.notification.controller;

import com.soa.fooddelivery.notification.dto.NotificationDto;
import com.soa.fooddelivery.notification.dto.NotificationTemplateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    @PostMapping("/notification/template")
    public ResponseEntity<NotificationTemplateDto> createNotificationTemplate(@RequestBody NotificationTemplateDto request) {
        NotificationTemplateDto response = new NotificationTemplateDto();
        response.setNotificationId("xxx");
        response.setTitle(request.getTitle());
        response.setMessage(request.getMessage());
        response.setCategory(request.getCategory());
        response.setActive(true);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/notification/template")
    public ResponseEntity<NotificationTemplateDto> updateNotificationTemplate(@RequestBody NotificationTemplateDto request) {
        NotificationTemplateDto response = new NotificationTemplateDto();
        response.setNotificationId(request.getNotificationId());
        response.setTitle(request.getTitle());
        response.setMessage(request.getMessage());
        response.setCategory(request.getCategory());
        response.setActive(request.getActive());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/notification/template/{notificationId}")
    public ResponseEntity<NotificationTemplateDto> DeleteNotificationTemplate(@PathVariable(name = "notificationId") String notificationId) {
        NotificationTemplateDto response = new NotificationTemplateDto();
        response.setNotificationId(notificationId);
        response.setActive(false);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/notification/templates")
    public ResponseEntity<NotificationTemplateDto[]> getNotificationTemplatesList() {
        NotificationTemplateDto notificationTemplate = getDummyNotificationTemplate();
        NotificationTemplateDto[] response = {notificationTemplate, notificationTemplate, notificationTemplate};
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/notification/template/{notificationId}")
    public ResponseEntity<NotificationTemplateDto> getNotificationTemplate(@PathVariable(name = "notificationId") String notificationId) {
        NotificationTemplateDto response = getDummyNotificationTemplate();
        response.setNotificationId(notificationId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/notification/send")
    public ResponseEntity<NotificationDto> sendNotification(@RequestBody NotificationDto request) {
        NotificationDto response = new NotificationDto();
        response.setUserId(request.getUserId());
        response.setNotificationId(request.getNotificationId());
        response.setStatus("success");
        return ResponseEntity.ok().body(response);
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
