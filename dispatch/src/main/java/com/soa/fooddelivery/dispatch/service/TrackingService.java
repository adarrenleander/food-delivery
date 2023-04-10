package com.soa.fooddelivery.dispatch.service;

import com.soa.fooddelivery.dispatch.configuration.TrackingConfiguration;
import com.soa.fooddelivery.dispatch.dto.TrackingDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrackingService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TrackingService.class);
    @Autowired private TrackingConfiguration trackingConfiguration;
    @Autowired private RestTemplateBuilder restTemplateBuilder;

    public String getTrackingUrl(Integer dispatchId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = trackingConfiguration.getHost() + trackingConfiguration.getTrackPath().replace("{dispatchId}", dispatchId.toString());

        TrackingDto res = restTemplate.getForObject(url, TrackingDto.class);
        log.info("GET TRACKING URL dispatch:" + dispatchId);

        // TODO: handle unhappy flow

        return res.getTrackingUrl();
    }
}
