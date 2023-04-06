package com.soa.fooddelivery.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DispatchDto {
    private String orderId;
    private String dispatchId;  // only used in response
    private String driverId;
    private String status;  // created / finding driver / accepted / on delivery / delivered / rejected
    private DispatchDetailsDto restaurant;
    private DispatchDetailsDto receiver;
    private String time;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DispatchDetailsDto getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(DispatchDetailsDto restaurant) {
        this.restaurant = restaurant;
    }

    public DispatchDetailsDto getReceiver() {
        return receiver;
    }

    public void setReceiver(DispatchDetailsDto receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
