package com.soa.fooddelivery.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItemDto {
    private String id;
    private String name;
    private Float price;
    private String detail;
    private MenuDto menu;
    private Boolean ActiveStatus;
}

