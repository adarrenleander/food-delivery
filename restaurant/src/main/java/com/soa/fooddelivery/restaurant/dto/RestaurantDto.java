package com.soa.fooddelivery.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantDto {
    private String id;
    private String name;
    private String address;

    public RestaurantDto(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public RestaurantDto() {
    }
}
