package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDto {
    private String id;
    private String code;
    private Float discount;
    private Integer loyaltyPoint;
    private Boolean activeStatus;

    public PromotionDto(String id, String code, Float discount, Boolean activeStatus, Integer loyaltyPoint) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.activeStatus = activeStatus;
        this.loyaltyPoint = loyaltyPoint;
    }

    public PromotionDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(Integer loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}

