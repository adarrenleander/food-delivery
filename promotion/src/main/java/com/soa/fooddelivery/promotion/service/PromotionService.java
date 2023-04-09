package com.soa.fooddelivery.promotion.service;

import com.soa.fooddelivery.promotion.dto.PromotionDto;
import com.soa.fooddelivery.promotion.dto.PromotionUserDto;
import com.soa.fooddelivery.promotion.dto.UserDto;
import com.soa.fooddelivery.promotion.entity.Promotion;
import com.soa.fooddelivery.promotion.entity.PromotionUser;
import com.soa.fooddelivery.promotion.repository.PromotionRepository;
import com.soa.fooddelivery.promotion.repository.PromotionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromotionService {
    @Autowired
    PromotionUserRepository promotionUserRepository;

    @Autowired
    PromotionRepository promotionRepository;

    @Transactional(rollbackFor={Exception.class})
    public PromotionDto deletePromotion (Integer id){
        Promotion promotion = promotionRepository.findAllById(id).get(0);
        promotion.setActiveStatus(false);
        promotionRepository.save(promotion);
        return promotion.convertToDto();
    }

    @Transactional(rollbackFor={Exception.class})
    public PromotionDto createPromotion (PromotionDto promotionDto){
        Promotion promotion = new Promotion();
        promotion.setCode(promotionDto.getCode());
        promotion.setDiscount(promotionDto.getDiscount());
        promotion.setLoyaltyPoint(promotionDto.getLoyaltyPoint());
        promotion.setActiveStatus(true);
        promotionRepository.save(promotion);
        return promotion.convertToDto();
    }

    @Transactional(rollbackFor={Exception.class})
    public PromotionDto updatePromotion (PromotionDto promotionDto){
        Promotion promotion = promotionRepository.findAllById(promotionDto.getId()).get(0);
        promotion.setCode(promotionDto.getCode());
        promotion.setDiscount(promotionDto.getDiscount());
        promotion.setLoyaltyPoint(promotionDto.getLoyaltyPoint());
        promotionRepository.save(promotion);
        return promotion.convertToDto();
    }

    @Transactional(rollbackFor={Exception.class})
    public PromotionUserDto applyPromotion (PromotionUserDto promotionUserDto){
        PromotionUser promotionUser = promotionUserRepository.getPromotionUserByPromotionIdAndUserId(promotionUserDto.getPromotion().getId(), promotionUserDto.getUser().getId()).get(0);
        promotionUser.setUsageStatus("Success");
        return promotionUser.convertToDto(promotionRepository);
    }

    @Transactional(rollbackFor={Exception.class})
    public PromotionUserDto grantPromotion (PromotionUserDto promotionUserDto){
        PromotionUser promotionUser = promotionUserRepository.getPromotionUserByPromotionIdAndUserId(promotionUserDto.getPromotion().getId(), promotionUserDto.getUser().getId()).get(0);
        promotionUser.setPromotion(promotionRepository.findAllById(promotionUserDto.getPromotion().getId()).get(0));
        promotionUser.setUserId(promotionUserDto.getUser().getId());
        return promotionUser.convertToDto(promotionRepository);
    }
}
