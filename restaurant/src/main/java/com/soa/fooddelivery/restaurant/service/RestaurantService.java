package com.soa.fooddelivery.restaurant.service;

import com.soa.fooddelivery.restaurant.dto.RestaurantDto;
import com.soa.fooddelivery.restaurant.entity.Restaurant;
import com.soa.fooddelivery.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;


    @Transactional(rollbackFor={Exception.class})
    public RestaurantDto updateRestaurant (RestaurantDto restaurantDto){
        Restaurant restaurant = restaurantRepository.findAllById(restaurantDto.getId()).get(0);
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurantRepository.save(restaurant);
        return restaurant.convertToDto();
    }

    @Transactional(rollbackFor={Exception.class})
    public RestaurantDto createRestaurant (RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurantRepository.save(restaurant);
        return restaurant.convertToDto();
    }

}
