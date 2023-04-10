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
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Integer id) {
        UserDto response = userService.getUserById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<UserDto>> getAvailableDrivers() {
        List<UserDto> response = userRepository.findAvailableDrivers();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/driver/{driverId}/available")
    public ResponseEntity<UserDto> setDriverAvailable(@PathVariable Integer driverId) {
        UserDto response = userService.setDriverAvailability(driverId, true);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/driver/{driverId}/unavailable")
    public ResponseEntity<UserDto> setDriverUnavailable(@PathVariable Integer driverId) {
        UserDto response = userService.setDriverAvailability(driverId, false);
        return ResponseEntity.ok().body(response);
    }
}
