package com.soa.fooddelivery.dispatch.dto;

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
    private Integer orderId;
    private Integer dispatchId;  // only used in response
    private Integer driverId;
    private String status;  // created / finding driver / accepted / on delivery / delivered
    private DispatchDetailsDto restaurant;
    private DispatchDetailsDto receiver;
    private String time;
    private String trackingUrl;

    public String getTrackingUrl() {
        return trackingUrl;
    }

    public void setTrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
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

    public DispatchDto(Integer dispatchId,Integer orderId,  Integer driverId, String status, String time) {
        this.orderId = orderId;
        this.dispatchId = dispatchId;
        this.driverId = driverId;
        this.status = status;
        this.time = time;
    }

    public DispatchDto() {
    }
}
