package com.soa.fooddelivery.restaurant.repository;

import com.soa.fooddelivery.restaurant.dto.MenuItemDto;
import com.soa.fooddelivery.restaurant.entity.MenuItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, String> {

    List<MenuItem> findAllById(@Param("id") Integer id);

    @Query("SELECT new com.soa.fooddelivery.restaurant.dto.MenuItemDto(m.id, m.name, m.price, m.detail, m.activeStatus) FROM MenuItem m WHERE m.activeStatus = true ORDER BY m.name DESC")
    List<MenuItemDto> findAllOrderByName();

    @Query("SELECT new com.soa.fooddelivery.restaurant.dto.MenuItemDto(m.id, m.name, m.price, m.detail, m.activeStatus) FROM MenuItem m WHERE m.activeStatus = true and m.id=:id")
    MenuItemDto findDtoById(@Param("id") Integer id);
}
