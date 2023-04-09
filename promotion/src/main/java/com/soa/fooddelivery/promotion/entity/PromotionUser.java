package com.soa.fooddelivery.promotion.entity;

import com.soa.fooddelivery.promotion.dto.PromotionUserDto;
import com.soa.fooddelivery.promotion.repository.PromotionRepository;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table
public class PromotionUser {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Promotion promotion;
    private Integer userId;
    private String usageStatus;


    public PromotionUserDto convertToDto(PromotionRepository promotionRepository){
        PromotionUserDto result = new PromotionUserDto();
        result.setUsageStatus(usageStatus);
        result.setPromotion(promotionRepository.findAllById(promotion.getId()).get(0).convertToDto());
        return result;
    }
}
