package com.soa.fooddelivery.loyalty.repository;

import com.soa.fooddelivery.loyalty.entity.Loyalty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoyaltyRepository extends CrudRepository<Loyalty, String> {

    List<Loyalty> findAllByUserId(@Param("id") Integer id);
}
