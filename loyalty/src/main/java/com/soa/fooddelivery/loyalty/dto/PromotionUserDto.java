package com.soa.fooddelivery.loyalty.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.catalina.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionUserDto {
    private PromotionDto promotion;
    private UserDto user;
    private Float totalAmount;
}

