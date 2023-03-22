package com.soa.fooddelivery.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * {
 *   "orderId": "xxx",
 *   "userId": "xxx",
 *   "totalAmount": 15
 * }
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {
    private String orderId;
    private String userId;
    private Float totalAmount;
}
