package com.soa.fooddelivery.dispatch.service;

import com.soa.fooddelivery.dispatch.configuration.UserConfiguration;
import com.soa.fooddelivery.dispatch.dto.UserDto;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
    @Autowired private UserConfiguration userConfiguration;
    @Autowired private RestTemplateBuilder restTemplateBuilder;

    public UserDto[] getAvailableDrivers() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = userConfiguration.getHost() + userConfiguration.getDriversPath();

        UserDto[] res = restTemplate.getForObject(url, UserDto[].class);
        log.info("GET AVAILABLE DRIVERS size:" + res.length);

        // TODO: handle unhappy flow

        return res;
    }

    public UserDto setDriverAvailable(Integer driverId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = userConfiguration.getHost() + userConfiguration.getSetDriverAvailablePath().replace("{driverId}", driverId.toString());

        UserDto res = restTemplate.postForObject(url, null, UserDto.class);
        log.info("SET DRIVER AVAILABLE id:" + driverId);

        // TODO: handle unhappy flow

        return res;
    }

    public UserDto setDriverUnavailable(Integer driverId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = userConfiguration.getHost() + userConfiguration.getSetDriverUnavailablePath().replace("{driverId}", driverId.toString());

        UserDto res = restTemplate.postForObject(url, null, UserDto.class);
        log.info("SET DRIVER UNAVAILABLE id:" + driverId);

        // TODO: handle unhappy flow

        return res;
    }
}
