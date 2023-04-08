package com.soa.fooddelivery.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDto {
    private Integer id;
    private String name;
    private String detail;
    private RestaurantDto restaurant;
    private List<MenuItemDto> menuItems;
    private Boolean activeStatus;

    public MenuDto() {
    }

    public MenuDto(Integer id, String name, String detail, Boolean activeStatus) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.activeStatus = activeStatus;
    }
}

