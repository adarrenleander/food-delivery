package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * {
 *   "menuItemId": "xxx",
 *   "quantity": 1,
 *   "notes": "xxx",
 *   "price": 2
 * }
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto {
    private String menuItemId;
    private Integer quantity;
    private String notes;
    private Float price;
}
