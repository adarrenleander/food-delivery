package com.soa.fooddelivery.tracking.service;

import com.soa.fooddelivery.tracking.dto.OrderTrackingDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TrackingService.class);

    public OrderTrackingDto getOrderTracking(String dispatchId) {
        // the order tracking is integrated with a third-party provider
        // who can provide a url for a live map
        // this implementation is outside the scope of this project

        OrderTrackingDto res = new OrderTrackingDto();
        res.setDispatchId(dispatchId);
        res.setTrackingURL("https://www.google.com");

        return res;
    }
}
