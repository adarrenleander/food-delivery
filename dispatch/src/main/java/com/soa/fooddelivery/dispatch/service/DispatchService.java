package com.soa.fooddelivery.dispatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.fooddelivery.dispatch.dto.DispatchDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class DispatchService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DispatchService.class);
    @Value("${mq.topic.find-driver}") private String mqTopicFindDriver;
    @Autowired private JmsTemplate jmsTemplate;

    public DispatchDto dispatchOrder(DispatchDto dispatch) {
        // TODO: save dispatch to DB
        Integer dispatchId = 1;
        dispatch.setDispatchId(dispatchId);

        publishFindDriver(dispatch);

        DispatchDto res = new DispatchDto();
        res.setDispatchId(dispatchId);
        res.setStatus("created");
        return res;
    }

    public DispatchDto acceptDispatch(Integer dispatchId) {
        // TODO: update to DB as accepted

        DispatchDto res = new DispatchDto();
        res.setDispatchId(dispatchId);
        res.setStatus("accepted");
        res.setDriverId(1);
        return res;
    }

    public DispatchDto rejectDispatch(Integer dispatchId) {
        // TODO: query DB for dispatch by dispatchId
        DispatchDto dispatch = new DispatchDto();

        publishFindDriver(dispatch);

        DispatchDto res = new DispatchDto();
        res.setDispatchId(dispatchId);
        res.setStatus("finding driver");
        return res;
    }

    public DispatchDto updateDispatchStatus(DispatchDto dispatch) {
        // TODO: update status in DB according to request

        DispatchDto res = new DispatchDto();
        res.setDispatchId(dispatch.getDispatchId());
        res.setStatus(dispatch.getStatus());
        return res;
    }

    public DispatchDto getDispatchStatus(Integer dispatchId) {
        // TODO: query from DB based on dispatchId

        // TODO: hit tracking service to get tracking information

        DispatchDto res = new DispatchDto();
        res.setDispatchId(dispatchId);
        res.setStatus("on delivery");
        return res;
    }

    public void publishFindDriver(DispatchDto dispatch) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            jmsTemplate.convertAndSend(mqTopicFindDriver, mapper.writeValueAsString(dispatch));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed convert to JSON string in " + mqTopicFindDriver);
        }
    }
}
