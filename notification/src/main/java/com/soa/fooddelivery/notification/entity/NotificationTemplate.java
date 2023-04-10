package com.soa.fooddelivery.notification.entity;

import com.soa.fooddelivery.notification.dto.NotificationTemplateDto;

import javax.persistence.*;

@Entity
@Table
public class NotificationTemplate {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String message;
    private String category;
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public NotificationTemplateDto convertToDto() {
        return new NotificationTemplateDto(id, title, message, category, active);
    }
}
