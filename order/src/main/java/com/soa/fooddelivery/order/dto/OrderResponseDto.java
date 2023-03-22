package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * {
 *   "orderId": "xxx",
 *   "status": "xxx"
 * }
 */

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponseDto {
    private String orderId;
    private String status; // placed / completed / canceled
}