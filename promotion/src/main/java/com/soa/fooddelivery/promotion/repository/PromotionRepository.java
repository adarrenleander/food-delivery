package com.soa.fooddelivery.promotion.repository;

import com.soa.fooddelivery.promotion.dto.PromotionDto;
import com.soa.fooddelivery.promotion.entity.Promotion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, String> {


    List<Promotion> findAllById(@Param("id") Integer id);
    List<Promotion> findAllByCode(@Param("code") String code);
    @Query("SELECT new com.soa.fooddelivery.promotion.dto.PromotionDto(m.id, m.code, m.discount, m.activeStatus, m.loyaltyPoint) FROM Promotion m WHERE m.activeStatus = true ORDER BY m.discount DESC")
    List<PromotionDto> findAllOrderByDiscount();

    @Query("SELECT new com.soa.fooddelivery.promotion.dto.PromotionDto(m.id, m.code, m.discount, m.activeStatus, m.loyaltyPoint) FROM Promotion m WHERE m.activeStatus = true and m.code=:code")
    PromotionDto getPromotionByCode(@Param("code") String code);

}
