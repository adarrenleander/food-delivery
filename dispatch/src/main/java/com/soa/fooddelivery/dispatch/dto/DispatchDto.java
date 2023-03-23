package com.soa.fooddelivery.dispatch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * {
 *   "orderId": "xxx",
 *   "dispatchId": "xxx",
 *   "status": "xxx",
 *   "restaurant": {
 *     "name": "xxx",
 *     "address": "xxx",
 *     "phoneNumber": "xxx"
 *   },
 *   "receiver": {
 *     "name": "xxx",
 *     "address": "xxx",
 *     "phoneNumber": "xxx"
 *   },
 *   "time": "yyyy-mm-dd hh:mm"
 * }
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DispatchDto {
    private String orderId;
    private String dispatchId;  // only used in response
    private String driverId;
    private String status;  // created / finding driver / accepted / delivered / rejected
    private DispatchDetailsDto restaurant;
    private DispatchDetailsDto receiver;
    private String time;
}
