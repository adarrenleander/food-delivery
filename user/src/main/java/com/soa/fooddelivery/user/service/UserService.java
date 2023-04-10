package com.soa.fooddelivery.user.service;

import com.soa.fooddelivery.user.dto.LoyaltyDto;
import com.soa.fooddelivery.user.dto.UserDto;
import com.soa.fooddelivery.user.entity.User;
import com.soa.fooddelivery.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired UserRepository userRepository;
    @Autowired LoyaltyService loyaltyService;

    @Transactional(rollbackFor={Exception.class})
    public UserDto deleteUser (Integer id){
        User user = userRepository.findAllById(id).get(0);
        user.setActiveStatus(false);
        userRepository.save(user);
        return user.convertToDto();
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserDto createUser(UserDto userDto){

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setActiveStatus(true);
        user.setCategory(userDto.getCategory());
        if (user.getCategory().equals("driver")) {
            user.setIsAvailable(true);
        }
        user = userRepository.save(user);
        userDto = user.convertToDto();

        LoyaltyDto loyalty = loyaltyService.createLoyaltyUser(user);
        userDto.setLoyaltyPoints(loyalty.getLoyaltyPoint());

        return userDto;
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserDto updateUser(UserDto userDto){
        User user = userRepository.findAllById(userDto.getId()).get(0);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCategory(userDto.getCategory());
        if (user.getCategory().equals("driver")) {
            user.setIsAvailable(userDto.getIsAvailable());
        }
        userRepository.save(user);
        return user.convertToDto();
    }

    public UserDto getUserById(Integer id) {
        UserDto userDto = userRepository.findDtoById(id);

        LoyaltyDto loyalty = loyaltyService.getLoyaltyUser(id);
        userDto.setLoyaltyPoints(loyalty.getLoyaltyPoint());

        return userDto;
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserDto setDriverAvailability(Integer driverId, Boolean availability) {
        User user = userRepository.findAllById(driverId).get(0);
        user.setIsAvailable(availability);
        userRepository.save(user);
        return user.convertToDto();
    }
}
