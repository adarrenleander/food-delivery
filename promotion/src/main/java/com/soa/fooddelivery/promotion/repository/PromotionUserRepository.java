package com.soa.fooddelivery.promotion.repository;

import com.soa.fooddelivery.promotion.dto.PromotionDto;
import com.soa.fooddelivery.promotion.entity.PromotionUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionUserRepository extends CrudRepository<PromotionUser, String> {

    List<PromotionUser> findAllById(@Param("id") Integer id);

    @Query("SELECT pu FROM PromotionUser pu JOIN Promotion p where p.id=:promotionId and pu.userId=:userId and p.activeStatus=true")
    List<PromotionUser> getPromotionUserByPromotionIdAndUserId(@Param("promotionId") Integer promotionId, @Param("userId") Integer userId);

    @Query("SELECT new com.soa.fooddelivery.promotion.dto.PromotionDto(m.id, m.code, m.discount, m.activeStatus, m.loyaltyPoint) FROM PromotionUser p JOIN p.promotion m WHERE m.activeStatus = true and p.userId = :userId ORDER BY m.discount DESC")
    List<PromotionDto> getPromotionByUserIdOrderByDiscount(@Param("userId") Integer userId);


}
