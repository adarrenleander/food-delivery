package com.soa.fooddelivery.loyalty.service;

import com.soa.fooddelivery.loyalty.dto.LoyaltyDto;
import com.soa.fooddelivery.loyalty.dto.PromotionDto;
import com.soa.fooddelivery.loyalty.dto.PromotionUserDto;
import com.soa.fooddelivery.loyalty.dto.UserDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoyaltyService.class);
    @Autowired private PromotionService promotionService;

    public LoyaltyDto grantLoyalty(LoyaltyDto request) {
        // TODO: add granted loyalty points from request to DB based on userId
        Integer currLoyaltyPoints = 5;

        LoyaltyDto res = new LoyaltyDto();
        UserDto user = new UserDto();
        user.setId(request.getUser().getId());
        res.setUser(user);
        res.setLoyaltyPoint(request.getLoyaltyPoint() + currLoyaltyPoints);

        return res;
    }

    public LoyaltyDto redeemLoyalty(LoyaltyDto request) {
        // TODO: get loyalty by userId
        Integer currLoyaltyPoints = 20;

        LoyaltyDto res = new LoyaltyDto();
        res.setUser(request.getUser());
        PromotionDto promotion = new PromotionDto();
        promotion.setCode(request.getPromotion().getCode());
        res.setPromotion(promotion);

        if (request.getPromotion().getLoyaltyPoint() > currLoyaltyPoints) {
            res.setRedeemStatus("insufficient balance");
            return res;
        }

        PromotionUserDto resPromo = promotionService.grantPromotion(request);
        if (!resPromo.getActiveStatus()) {
            res.setRedeemStatus("failed");
            return res;
        }

        //res.setLoyaltyPoint(res.getLoyaltyPoint() - resPromo.getPromotion().getLoyaltyPoint());\
        // TODO: subtract loyalty points in DB
        res.setRedeemStatus("success");

        return res;
    }

    public LoyaltyDto getLoyalty(String userId) {
        // TODO: query from DB based on userId

        LoyaltyDto res = new LoyaltyDto();
        UserDto user = new UserDto();
        user.setId(userId);
        res.setUser(user);
        res.setLoyaltyPoint(24);

        return res;
    }
}
