package com.soa.fooddelivery.order.service;

import com.soa.fooddelivery.order.configuration.PromotionConfiguration;
import com.soa.fooddelivery.order.dto.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PromotionService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PromotionService.class);
    @Autowired
    private PromotionConfiguration promotionConfiguration;
    @Autowired private RestTemplateBuilder restTemplateBuilder;

    public PromotionEligibilityDto checkPromotionEligiblity(OrderDto order) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = promotionConfiguration.getHost() + promotionConfiguration.getEligibilityPath();

        PromotionDto promo = new PromotionDto();
        promo.setCode(order.getPromotionCode());
        UserDto user = new UserDto();
        user.setId(order.getUserId());
        PromotionUserDto req = new PromotionUserDto();
        req.setPromotion(promo);
        req.setUser(user);
        req.setTotalAmount(order.getTotalAmount());

        PromotionEligibilityDto res = restTemplate.postForObject(url, req, PromotionEligibilityDto.class);
        log.info("CHECK PROMOTION ELIGIBILITY order:" + order.getOrderId() + ", promotion:" + req.getPromotion().getCode() + ", eligibility:" + res.getEligibility());

        // TODO: handle unhappy flow

        return res;
    }

    public PromotionUserDto applyPromotion(OrderDto order) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = promotionConfiguration.getHost() + promotionConfiguration.getApplyPath();

        PromotionDto promo = new PromotionDto();
        promo.setCode(order.getPromotionCode());
        UserDto user = new UserDto();
        user.setId(order.getUserId());
        PromotionUserDto req = new PromotionUserDto();
        req.setPromotion(promo);
        req.setUser(user);

        PromotionUserDto res = restTemplate.postForObject(url, req, PromotionUserDto.class);
        log.info("APPLY PROMOTION user:" + order.getUserId() + ", promo:" + order.getPromotionCode());

        // TODO: handle unhappy flow

        return res;
    }
}
