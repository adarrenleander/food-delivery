package com.soa.fooddelivery.loyalty.service;

import com.soa.fooddelivery.loyalty.dto.LoyaltyDto;
import com.soa.fooddelivery.loyalty.dto.PromotionDto;
import com.soa.fooddelivery.loyalty.dto.PromotionUserDto;
import com.soa.fooddelivery.loyalty.dto.UserDto;
import com.soa.fooddelivery.loyalty.entity.Loyalty;
import com.soa.fooddelivery.loyalty.repository.LoyaltyRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoyaltyService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoyaltyService.class);

    @Autowired
    LoyaltyRepository loyaltyRepository;

    @Autowired
    PromotionService promotionService;


    @Transactional(rollbackFor = {Exception.class})
    public LoyaltyDto createLoyalty(LoyaltyDto loyaltyDto){
        Loyalty loyalty = new Loyalty();
        loyalty.setUserId(loyaltyDto.getUser().getId());
        loyalty.setLoyaltyPoint(0);
        loyaltyRepository.save(loyalty);
        return loyalty.convertToDto();
    }

    public LoyaltyDto grantLoyalty(LoyaltyDto request) {
        Loyalty loyalty = loyaltyRepository.findAllByUserId(request.getUser().getId()).get(0);
        Integer currLoyaltyPoints = loyalty.getLoyaltyPoint();
        loyalty.setLoyaltyPoint(request.getLoyaltyPoint() + currLoyaltyPoints);
        loyalty = loyaltyRepository.save(loyalty);
        return loyalty.convertToDto();
    }

    public LoyaltyDto redeemLoyalty(LoyaltyDto request) {
        Loyalty loyalty = loyaltyRepository.findAllByUserId(request.getUser().getId()).get(0);
        Integer currLoyaltyPoints = loyalty.getLoyaltyPoint();

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

        res.setRedeemStatus("success");
        loyalty.setLoyaltyPoint(currLoyaltyPoints-request.getPromotion().getLoyaltyPoint());
        loyalty = loyaltyRepository.save(loyalty);
        res.setLoyaltyPoint(loyalty.getLoyaltyPoint());
        return res;
    }

  /*  public LoyaltyDto getLoyalty(Integer userId) {
        // TODO: query from DB based on userId

        LoyaltyDto res = new LoyaltyDto();
        UserDto user = new UserDto();
        user.setId(userId);
        res.setUser(user);
        res.setLoyaltyPoint(24);

        return res;
    }

   */
}
