package com.soa.fooddelivery.loyalty.service;

import com.soa.fooddelivery.loyalty.configuration.PromotionConfiguration;
import com.soa.fooddelivery.loyalty.dto.LoyaltyDto;
import com.soa.fooddelivery.loyalty.dto.PromotionUserDto;
import com.soa.fooddelivery.loyalty.dto.UserDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PromotionService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PromotionService.class);
    @Autowired private PromotionConfiguration promotionConfiguration;
    @Autowired private RestTemplateBuilder restTemplateBuilder;

    public PromotionUserDto grantPromotion(LoyaltyDto loyalty) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = promotionConfiguration.getHost() + promotionConfiguration.getGrantPath();

        PromotionUserDto request = new PromotionUserDto();
        request.setPromotion(loyalty.getPromotion());
        request.setUser(loyalty.getUser());

        PromotionUserDto res = restTemplate.postForObject(url,request, PromotionUserDto.class);
        log.info("GRANT PROMOTION promotion:" + request.getPromotion().getCode() + ", user:" + request.getUser().getId());

        // TODO: handle unhappy flow

        return res;
    }
}
