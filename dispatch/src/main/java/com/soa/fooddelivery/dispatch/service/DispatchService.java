package com.soa.fooddelivery.dispatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.fooddelivery.dispatch.dto.DispatchDto;
import com.soa.fooddelivery.dispatch.entity.Dispatch;
import com.soa.fooddelivery.dispatch.repository.DispatchRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    @Autowired
    DispatchRepository dispatchRepository;

    public DispatchDto dispatchOrder(DispatchDto dispatchDto) {
        Dispatch dispatch = new Dispatch();
        dispatch.setOrderId(dispatchDto.getOrderId());
        dispatch = dispatchRepository.save(dispatch);
        publishFindDriver(dispatch);
        dispatch.setStatus("created");
        DispatchDto res = new DispatchDto();
        res.setDispatchId(dispatch.getId());
        res.setStatus("created");
        return res;
    }

    public DispatchDto acceptDispatch(Integer dispatchId) {
        Dispatch dispatch = dispatchRepository.findAllById(dispatchId).get(0);
        dispatch.setStatus("accepted");
        dispatch = dispatchRepository.save(dispatch);
        DispatchDto res = dispatch.convertToDto();
        return res;
    }

    public DispatchDto rejectDispatch(Integer dispatchId) {
        Dispatch dispatch = dispatchRepository.findAllById(dispatchId).get(0);
        dispatch.setStatus("finding driver");
        dispatch = dispatchRepository.save(dispatch);
        publishFindDriver(dispatch);
        DispatchDto res = dispatch.convertToDto();
        return res;
    }

    public DispatchDto updateDispatchStatus(DispatchDto dispatchDto) {
        Dispatch dispatch = dispatchRepository.findAllById(dispatchDto.getDispatchId()).get(0);
        dispatch.setStatus("finding driver");
        dispatch = dispatchRepository.save(dispatch);
        return dispatch.convertToDto();
    }

    public DispatchDto getDispatchStatus(Integer dispatchId) {

        Dispatch dispatch = dispatchRepository.findAllById(dispatchId).get(0);
        dispatch.setStatus("on delivery");
        dispatch = dispatchRepository.save(dispatch);
        return dispatch.convertToDto();
        // TODO: hit tracking service to get tracking information
    }

    public void publishFindDriver(Dispatch dispatch) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            jmsTemplate.convertAndSend(mqTopicFindDriver, mapper.writeValueAsString(dispatch));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed convert to JSON string in " + mqTopicFindDriver);
        }
    }
}
