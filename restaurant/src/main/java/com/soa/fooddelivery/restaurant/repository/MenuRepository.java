package com.soa.fooddelivery.restaurant.repository;

import com.soa.fooddelivery.restaurant.dto.MenuDto;
import com.soa.fooddelivery.restaurant.dto.RestaurantDto;
import com.soa.fooddelivery.restaurant.entity.Menu;
import com.soa.fooddelivery.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, String> {

    List<Menu> findAllById(@Param("id") Integer id);

    @Query("SELECT new com.soa.fooddelivery.restaurant.dto.MenuDto(m.id, m.name, m.detail, m.activeStatus) FROM Menu m WHERE m.activeStatus = true ORDER BY m.name DESC")
    List<MenuDto> findAllOrderByName();

    @Query("SELECT new com.soa.fooddelivery.restaurant.dto.MenuDto(m.id, m.name, m.detail, m.activeStatus) FROM Menu m WHERE m.activeStatus = true and m.id=:id")
    MenuDto findDtoById(@Param("id") Integer id);
}
