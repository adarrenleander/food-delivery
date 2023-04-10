package com.soa.fooddelivery.user.dto;

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
    private String category; // customer,driver
    private Boolean activeStatus;
    private Boolean isAvailable; // only for driver
    private Integer loyaltyPoints;

    public UserDto(Integer id, String firstName, String lastName, String category, Boolean activeStatus, Boolean isAvailable) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
        this.activeStatus = activeStatus;
        this.isAvailable = isAvailable;
    }

    public UserDto() {
    }
}
