package com.soa.fooddelivery.user.controller;

import com.soa.fooddelivery.user.dto.UserDto;
import com.soa.fooddelivery.user.repository.UserRepository;
import com.soa.fooddelivery.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto request) {
        UserDto response = userService.createUser(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto request) {
        UserDto response = userService.updateUser(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDto> deleteUSer(@PathVariable(name = "id") Integer id) {
        UserDto response = userService.deleteUser(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Integer id){
        UserDto response = userRepository.findDtoById(id);
        return ResponseEntity.ok().body(response);
    }

}
