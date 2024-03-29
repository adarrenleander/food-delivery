package com.soa.fooddelivery.tracking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * {
 *   "dispatchId": "xxx",
 *   "trackingUrl": "xxx"
 * }
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderTrackingDto {
    private Integer dispatchId;
    private String trackingUrl;
}
