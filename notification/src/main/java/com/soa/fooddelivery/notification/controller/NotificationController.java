package com.soa.fooddelivery.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    @PostMapping("/notification/template")
    public ResponseEntity<Object> createNotificationTemplate(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }

    @PutMapping("/notification/template")
    public ResponseEntity<Object> updateNotificationTemplate(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }

    @DeleteMapping("/notification/template/{notifId}")
    public ResponseEntity<Object> cancelNotificationTemplate(@PathVariable(name = "notifId") String notifId) {
        return ResponseEntity.ok().body(new Object());
    }

    @GetMapping("/notification/templates")
    public ResponseEntity<Object> getNotificationTemplatesList() {
        return ResponseEntity.ok().body(new Object());
    }

    @GetMapping("/notification/template/{notifId}")
    public ResponseEntity<Object> getOrder(@PathVariable(name = "notifId") String notifId) {
        return ResponseEntity.ok().body(new Object());
    }

    @PostMapping("/notification/send")
    public ResponseEntity<Object> sendNotification(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }
}
