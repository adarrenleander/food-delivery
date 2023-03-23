package com.soa.fooddelivery.user.controller;

import com.soa.fooddelivery.user.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto request) {
        UserDto response = new UserDto();
        response.setId(request.getId());
        response.setFrontName(request.getFrontName());
        response.setLastName(request.getLastName());
        response.setCategory(request.getCategory());
        response.setActiveStatus(true);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto request) {
        UserDto response = new UserDto();
        //TODO: get restaurant where id=request.getId();
        response.setId(request.getId()); //not needed if the data already taken from db
        response.setFrontName(request.getFrontName());
        response.setLastName(request.getLastName());
        response.setCategory(request.getCategory());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDto> deleteUSer(@PathVariable(name = "id") String id) {
        UserDto response = new UserDto();
        response.setId(id);
        response.setActiveStatus(false);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") String id){
        UserDto response = new UserDto(id, "Juwita", "Pasaribu", "customer", true);
        return ResponseEntity.ok().body(response);
    }

}
