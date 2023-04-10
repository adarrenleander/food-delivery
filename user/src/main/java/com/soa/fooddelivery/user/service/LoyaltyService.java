package com.soa.fooddelivery.user.service;

import com.soa.fooddelivery.user.configuration.LoyaltyConfiguration;
import com.soa.fooddelivery.user.dto.LoyaltyDto;
import com.soa.fooddelivery.user.entity.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoyaltyService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoyaltyService.class);
    @Autowired private LoyaltyConfiguration loyaltyConfiguration;
    @Autowired private RestTemplateBuilder restTemplateBuilder;

    public LoyaltyDto createLoyaltyUser(User user) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = loyaltyConfiguration.getHost() + loyaltyConfiguration.getCreatePath();

        LoyaltyDto req = new LoyaltyDto();
        req.setUser(user.convertToDto());
        LoyaltyDto res = restTemplate.postForObject(url, req, LoyaltyDto.class);

        return res;
    }

    public LoyaltyDto getLoyaltyUser(Integer userId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = loyaltyConfiguration.getHost() + loyaltyConfiguration.getGetPath().replace("{userId}", userId.toString());

        LoyaltyDto res = restTemplate.getForObject(url, LoyaltyDto.class);

        return res;
    }
}
