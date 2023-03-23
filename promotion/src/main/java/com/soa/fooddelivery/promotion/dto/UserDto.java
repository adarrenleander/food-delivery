package com.soa.fooddelivery.promotion.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    private String frontName;
    private String lastName;
    private String category; //customer,driver
    private Boolean activeStatus;
}
