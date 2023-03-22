package com.soa.fooddelivery.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * {
 *   "transactionId": "xxx",
 *   "userId": "xxx",
 *   "refundAmount": 15
 * }
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefundDto {
    private String transactionId;
    private String userId;
    private Float refundAmount;
}
