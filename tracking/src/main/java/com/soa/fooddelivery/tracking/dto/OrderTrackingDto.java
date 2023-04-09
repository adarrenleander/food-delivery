package com.soa.fooddelivery.tracking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * {
 *   "dispatchId": "xxx",
 *   "trackingURL": "xxx"
 * }
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderTrackingDto {
    private String dispatchId;
    private String trackingURL;
}
