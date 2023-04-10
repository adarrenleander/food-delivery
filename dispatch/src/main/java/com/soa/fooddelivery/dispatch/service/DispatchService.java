package com.soa.fooddelivery.dispatch.service;

import com.soa.fooddelivery.dispatch.dto.DispatchDto;
import com.soa.fooddelivery.dispatch.entity.Dispatch;
import com.soa.fooddelivery.dispatch.repository.DispatchRepository;
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
    @Autowired private DispatchRepository dispatchRepository;
    @Autowired private TrackingService trackingService;
    @Autowired private UserService userService;

    public DispatchDto dispatchOrder(DispatchDto dispatchDto) {
        Dispatch dispatch = new Dispatch();
        dispatch.setOrderId(dispatchDto.getOrderId());
        dispatch.setStatus("created");
        dispatch.setTime(dispatch.getTime());
        dispatch = dispatchRepository.save(dispatch);
        dispatchDto = dispatch.convertToDto();

        publishFindDriver(dispatch.getId());

        dispatch.setStatus("finding driver");
        dispatchRepository.save(dispatch);

        return dispatchDto;
    }

    public DispatchDto acceptDispatch(Integer dispatchId) {
        Dispatch dispatch = dispatchRepository.findAllById(dispatchId).get(0);
        dispatch.setStatus("accepted");
        dispatch = dispatchRepository.save(dispatch);

        return dispatch.convertToDto();
    }

    public DispatchDto rejectDispatch(Integer dispatchId) {
        Dispatch dispatch = dispatchRepository.findAllById(dispatchId).get(0);
        dispatch.setStatus("finding driver");
        dispatch = dispatchRepository.save(dispatch);

        userService.setDriverAvailable(dispatch.getDriverId());

        publishFindDriver(dispatch.getId());

        return dispatch.convertToDto();
    }

    public DispatchDto updateDispatchStatus(DispatchDto dispatchDto) {
        Dispatch dispatch = dispatchRepository.findAllById(dispatchDto.getDispatchId()).get(0);
        dispatch.setStatus(dispatchDto.getStatus());
        dispatch = dispatchRepository.save(dispatch);

        if (dispatch.getStatus().equals("completed")) {
            userService.setDriverAvailable(dispatch.getDriverId());
        }

        return dispatch.convertToDto();
    }

    public DispatchDto getDispatchStatus(Integer dispatchId) {
        Dispatch dispatch = dispatchRepository.findAllById(dispatchId).get(0);
        DispatchDto res = dispatch.convertToDto();

        String trackingUrl = trackingService.getTrackingUrl(dispatchId);
        res.setTrackingUrl(trackingUrl);

        return res;
    }

    public void publishFindDriver(Integer dispatchId) {
        jmsTemplate.convertAndSend(mqTopicFindDriver, dispatchId);
    }
}
