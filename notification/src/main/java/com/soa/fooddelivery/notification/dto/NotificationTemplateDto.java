package com.soa.fooddelivery.notification.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * {
 *   "notificationId": "xxx",
 *   "title": "xxx",
 *   "message": "xxx",
 *   "category": "xxx",
 *   "active": true
 * }
 */

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationTemplateDto {
    private String notificationId;
    private String title;
    private String message;
    private String category;
    private Boolean active;
}
