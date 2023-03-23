package com.soa.fooddelivery.restaurant.controller;

import com.soa.fooddelivery.restaurant.dto.RestaurantDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {
    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto request) {
        RestaurantDto response = new RestaurantDto();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setAddress(request.getAddress());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/restaurant")
    public ResponseEntity<RestaurantDto> updateOrderStatus(@RequestBody RestaurantDto request) {
        RestaurantDto response = new RestaurantDto();
        //TODO: get restaurant where id=request.getId();
        response.setId(request.getId()); //not needed if the data already taken by db
        response.setName(request.getName());
        response.setAddress(request.getAddress());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurant(){
        List<RestaurantDto> response = getSampleFullRestaurant();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable(name = "id") String id){
        List<RestaurantDto> restaurants = getSampleFullRestaurant();
        RestaurantDto response = new RestaurantDto();
        for (RestaurantDto restaurantDto : restaurants){
            if (restaurantDto.getId().equals(id)){
                response = restaurantDto;
            }
        }
        return ResponseEntity.ok().body(response);
    }



    public List<RestaurantDto> getSampleFullRestaurant() {

        List<RestaurantDto> response = new ArrayList<>();
        RestaurantDto restaurant1 = new RestaurantDto();
        restaurant1.setId("123");
        restaurant1.setName("name123");
        restaurant1.setAddress("Drienerlolaan 5, 7522 NB Enschede");

        RestaurantDto restaurant2 = new RestaurantDto();
        restaurant2.setId("223");
        restaurant2.setName("name223");
        restaurant2.setAddress("Calslaan, 7522 MH Enschede");

        RestaurantDto restaurant3 = new RestaurantDto();
        restaurant3.setId("323");
        restaurant3.setName("name323");
        restaurant3.setAddress("Boulevard 1945, 7511 AE Enschede");
        response.add(restaurant1);
        response.add(restaurant2);
        response.add(restaurant3);
        return response;
    }
}
