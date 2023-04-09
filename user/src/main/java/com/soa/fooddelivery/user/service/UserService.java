package com.soa.fooddelivery.user.service;

import com.soa.fooddelivery.user.dto.UserDto;
import com.soa.fooddelivery.user.entity.User;
import com.soa.fooddelivery.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
        userRepository.save(user);
        return user.convertToDto();
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserDto updateUser(UserDto userDto){
        User user = userRepository.findAllById(userDto.getId()).get(0);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCategory(userDto.getCategory());
        userRepository.save(user);
        return user.convertToDto();
    }
}
