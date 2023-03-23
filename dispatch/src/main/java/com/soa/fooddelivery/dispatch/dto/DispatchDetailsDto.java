package com.soa.fooddelivery.dispatch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * {
 *   "name": "xxx",
 *   "address": "xxx",
 *   "phoneNumber": "xxx"
 * }
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DispatchDetailsDto {
    private String name;
    private String address;
    private String phoneNumber;
}
