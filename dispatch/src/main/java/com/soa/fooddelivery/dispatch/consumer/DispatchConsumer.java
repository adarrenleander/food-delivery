package com.soa.fooddelivery.dispatch.consumer;

import com.soa.fooddelivery.dispatch.dto.UserDto;
import com.soa.fooddelivery.dispatch.entity.Dispatch;
import com.soa.fooddelivery.dispatch.repository.DispatchRepository;
import com.soa.fooddelivery.dispatch.service.DispatchService;
import com.soa.fooddelivery.dispatch.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class DispatchConsumer {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DispatchConsumer.class);
    @Value("${mq.topic.find-driver}") private String mqTopicFindDriver;
    @Autowired private UserService userService;
    @Autowired private DispatchRepository dispatchRepository;
    @Autowired private DispatchService dispatchService;

    @JmsListener(destination = "${mq.topic.find-driver}")
    public void findDriver(Integer dispatchId) {
        log.info("Consumer \"" + mqTopicFindDriver + "\": Dispatch " + dispatchId);

        UserDto[] driverList = userService.getAvailableDrivers();
        if (driverList.length == 0) {
            dispatchService.publishFindDriver(dispatchId);
            throw new RuntimeException("All drivers are currently unavailable");
        }

        // a better algorithm could have been implemented,
        // but for simplification purposes,
        // we chose to randomize the driver selection process
        Random rand = new Random();
        UserDto driver = driverList[rand.nextInt(driverList.length)];

        Dispatch dispatch = dispatchRepository.findAllById(dispatchId).get(0);
        dispatch.setStatus("finding driver");
        dispatch.setDriverId(driver.getId());
        dispatchRepository.save(dispatch);

        // driver will be unavailable until completing dispatch or rejecting dispatch
        userService.setDriverUnavailable(driver.getId());

        // driver will accept or reject based on UI (no implemented in this project)
    }
}
