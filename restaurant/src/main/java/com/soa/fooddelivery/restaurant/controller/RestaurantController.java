package com.soa.fooddelivery.restaurant.controller;

import com.soa.fooddelivery.restaurant.dto.RestaurantDto;
import com.soa.fooddelivery.restaurant.entity.Restaurant;
import com.soa.fooddelivery.restaurant.repository.RestaurantRepository;
import com.soa.fooddelivery.restaurant.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto request) {
        RestaurantDto response = restaurantService.createRestaurant(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/restaurant")
    public ResponseEntity<RestaurantDto> updateOrderStatus(@RequestBody RestaurantDto request) {
        RestaurantDto response = restaurantService.updateRestaurant(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurant(){
        List<RestaurantDto> response = restaurantRepository.findAllOrderByName();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable(name = "id") Integer id){
        RestaurantDto response = restaurantRepository.findDtoById(id);
        return ResponseEntity.ok().body(response);
    }


/*
    public List<RestaurantDto> getSampleFullRestaurant() {

        List<RestaurantDto> response = new ArrayList<>();
        RestaurantDto restaurant1 = new RestaurantDto();
        restaurant1.setId(123);
        restaurant1.setName("name123");
        restaurant1.setAddress("Drienerlolaan 5, 7522 NB Enschede");

        RestaurantDto restaurant2 = new RestaurantDto();
        restaurant2.setId(223);
        restaurant2.setName("name223");
        restaurant2.setAddress("Calslaan, 7522 MH Enschede");

        RestaurantDto restaurant3 = new RestaurantDto();
        restaurant3.setId(323);
        restaurant3.setName("name323");
        restaurant3.setAddress("Boulevard 1945, 7511 AE Enschede");
        response.add(restaurant1);
        response.add(restaurant2);
        response.add(restaurant3);
        return response;
    }

 */
}
