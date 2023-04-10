package com.soa.fooddelivery.notification.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * {
 *   "notificationTemplateId": "xxx",
 *   "title": "xxx",
 *   "message": "xxx",
 *   "category": "xxx",
 *   "active": true
 * }
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationTemplateDto {
    private Integer notificationTemplateId;
    private String title;
    private String message;
    private String category;
    private Boolean active;

    public Integer getNotificationTemplateId() {
        return notificationTemplateId;
    }

    public void setNotificationTemplateId(Integer notificationTemplateId) {
        this.notificationTemplateId = notificationTemplateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public NotificationTemplateDto() {
    }

    public NotificationTemplateDto(Integer notificationTemplateId, String title, String message, String category, Boolean active) {
        this.notificationTemplateId = notificationTemplateId;
        this.title = title;
        this.message = message;
        this.category = category;
        this.active = active;
    }
}
