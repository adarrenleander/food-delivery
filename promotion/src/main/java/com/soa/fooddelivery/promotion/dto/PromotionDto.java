package com.soa.fooddelivery.promotion.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDto {
    private String id;
    private String code;
    private Float discount;
    private Boolean activeStatus;
}
