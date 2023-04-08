package com.soa.fooddelivery.restaurant.repository;

import com.soa.fooddelivery.restaurant.dto.RestaurantDto;
import com.soa.fooddelivery.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, String> {

    List<Restaurant> findAllById(@Param("id") Integer id);

    @Query("SELECT new com.soa.fooddelivery.restaurant.dto.RestaurantDto(r.id, r.name, r.address) FROM Restaurant r ORDER BY r.name DESC")
    List<RestaurantDto> findAllOrderByName();

    @Query("SELECT new com.soa.fooddelivery.restaurant.dto.RestaurantDto(r.id, r.name, r.address) FROM Restaurant r WHERE r.id=:id")
    RestaurantDto findDtoById(@Param("id") Integer id);
}
