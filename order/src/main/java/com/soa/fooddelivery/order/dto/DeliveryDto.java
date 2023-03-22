package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * {
 *   "name": "xxx",
 *   "address": "xxx",  // should split this up if time allows
 *   "phoneNumber": "xxx",
 *   "time": "yyyy-MM-dd hh:mm:ss"
 * }
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String time;

}
