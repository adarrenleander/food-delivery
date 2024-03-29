package com.soa.fooddelivery.promotion.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String category; //customer,driver
    private Boolean activeStatus;

    public UserDto(Integer id, String firstName, String lastName, String category, Boolean activeStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
        this.activeStatus = activeStatus;
    }

    public UserDto() {
    }
}
