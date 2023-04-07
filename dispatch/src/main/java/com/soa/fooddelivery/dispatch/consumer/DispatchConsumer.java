package com.soa.fooddelivery.dispatch.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.fooddelivery.dispatch.dto.DispatchDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DispatchConsumer {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DispatchConsumer.class);
    @Value("${mq.topic.find-driver}") private String mqTopicFindDriver;

    // TODO: configure retries
    @JmsListener(destination = "${mq.topic.find-driver}")
    public void findDriver(String message) {
        ObjectMapper mapper = new ObjectMapper();
        DispatchDto dispatch;
        try {
            dispatch = mapper.readValue(message, DispatchDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Consumer \"" + mqTopicFindDriver + "\": Dispatch " + dispatch.getDispatchId());

        // TODO: query all available drivers from DB

        // TODO: random pick from set of available drivers

        // TODO: update DB to set driverId

        // driver will accept or reject based on UI (no implemented in this project)
    }
}
