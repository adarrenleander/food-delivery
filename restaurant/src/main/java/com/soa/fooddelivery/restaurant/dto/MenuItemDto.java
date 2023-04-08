package com.soa.fooddelivery.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItemDto {
    private Integer id;
    private String name;
    private Float price;
    private String detail;
    private MenuDto menu;
    private Boolean ActiveStatus;

    public MenuItemDto() {
    }

    public MenuItemDto(Integer id, String name, Float price, String detail, Boolean activeStatus) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.ActiveStatus = activeStatus;
    }
}

