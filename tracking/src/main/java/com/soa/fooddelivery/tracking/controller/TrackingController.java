package com.soa.fooddelivery.tracking.controller;

import com.soa.fooddelivery.tracking.dto.OrderTrackingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackingController {

    @GetMapping("/dispatch/{dispatchId}/track")
    public ResponseEntity<OrderTrackingDto> getOrderTracking(@PathVariable(name = "dispatchId") String dispatchId) {
        OrderTrackingDto response = new OrderTrackingDto();
        response.setDispatchId(dispatchId);
        response.setDriverId("xxx");
        return ResponseEntity.ok().body(response);
    }
    // what is this api for?
}
