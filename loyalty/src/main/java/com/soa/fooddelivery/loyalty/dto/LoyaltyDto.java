package com.soa.fooddelivery.loyalty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoyaltyDto {
    private PromotionDto promotion;
    private UserDto user;
    private Integer loyaltyPoint;
    private String redeemStatus;
}
