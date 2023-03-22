package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * {
 *   "menuItemId": "xxx",
 *   "quantity": 1,
 *   "notes": "xxx",
 *   "price": 2
 * }
 */

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private String menuItemId;
    private Integer quantity;
    private String notes;
    private Float price;
}
