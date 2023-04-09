package com.soa.fooddelivery.promotion.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDto {
    private Integer id;
    private String code;
    private Float discount;
    private Integer loyaltyPoint;
    private Boolean activeStatus;

    public PromotionDto(Integer id, String code, Float discount, Boolean activeStatus, Integer loyaltyPoint) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.activeStatus = activeStatus;
        this.loyaltyPoint = loyaltyPoint;
    }

    public PromotionDto() {
    }
}
