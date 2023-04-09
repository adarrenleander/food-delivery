package com.soa.fooddelivery.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * {
 *   "transactionId": "xxx",
 *   "transactionType": "payment",
 *   "userId": "xxx",
 *   "orderId": "xxx",
 *   "status": "success"
 * }
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionStatusDto {
    private Integer transactionId;
    private String transactionType; // payment / refund
    private Integer userId;
    private Integer orderId;
    private String status; // only used in response -> success / in progress / failed
}
