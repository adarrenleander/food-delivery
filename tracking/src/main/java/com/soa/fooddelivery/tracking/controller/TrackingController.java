package com.soa.fooddelivery.tracking.controller;

import com.soa.fooddelivery.tracking.dto.OrderTrackingDto;
import com.soa.fooddelivery.tracking.service.TrackingService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackingController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TrackingController.class);
    @Autowired private TrackingService trackingService;

    @GetMapping("/dispatch/{dispatchId}/track")
    public ResponseEntity<OrderTrackingDto> getOrderTracking(@PathVariable(name = "dispatchId") Integer dispatchId) {
        log.debug("GET /dispatch/{dispatchId}/track getOrderTracking");
        OrderTrackingDto response = trackingService.getOrderTracking(dispatchId);
        return ResponseEntity.ok().body(response);
    }
}
