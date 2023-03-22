package com.soa.fooddelivery.notification.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
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

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationTemplateDto {
    private String notificationId;
    private String title;
    private String message;
    private String category;
    private Boolean active;
}
