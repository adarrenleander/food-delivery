package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * {
 *   "userId": "xxx",
 *   "notificationTemplateId": "xxx"
 * }
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotifyDto {
    private Integer userId;
    private Integer notificationTemplateId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNotificationTemplateId() {
        return notificationTemplateId;
    }

    public void setNotificationTemplateId(Integer notificationTemplateId) {
        this.notificationTemplateId = notificationTemplateId;
    }
}