package com.soa.fooddelivery.loyalty.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    private String frontName;
    private String lastName;
    private String category; //customer,driver
    private Boolean activeStatus;

    public UserDto(Integer id, String frontName, String lastName, String category, Boolean activeStatus) {
        this.id = id;
        this.frontName = frontName;
        this.lastName = lastName;
        this.category = category;
        this.activeStatus = activeStatus;
    }

    public UserDto() {
    }

    public UserDto(Integer id) {
        this.id = id;
    }
}