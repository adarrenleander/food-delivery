package com.soa.fooddelivery.order.service;

import com.soa.fooddelivery.order.configuration.RestaurantConfiguration;
import com.soa.fooddelivery.order.dto.RestaurantDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestaurantService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RestaurantService.class);
    @Autowired private RestaurantConfiguration restaurantConfiguration;
    @Autowired private RestTemplateBuilder restTemplateBuilder;

    public RestaurantDto getRestaurant(Integer restaurantId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = restaurantConfiguration.getHost() + restaurantConfiguration.getGetRestaurantPath() + restaurantId.toString();

        RestaurantDto res = restTemplate.getForObject(url, RestaurantDto.class);
        log.info("GET RESTAURANT id:" + res.getId() + ", name:" + res.getName());

        // TODO: handle unhappy flow

        return res;
    }
}
