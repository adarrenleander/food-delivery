package com.soa.fooddelivery.order.service;

import com.soa.fooddelivery.order.configuration.DispatchConfiguration;
import com.soa.fooddelivery.order.dto.DispatchDetailsDto;
import com.soa.fooddelivery.order.dto.DispatchDto;
import com.soa.fooddelivery.order.dto.OrderDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DispatchService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DispatchService.class);
    @Autowired private DispatchConfiguration dispatchConfiguration;
    @Autowired private RestTemplateBuilder restTemplateBuilder;

    public DispatchDto dispatchOrder(OrderDto order) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = dispatchConfiguration.getHost() + dispatchConfiguration.getDispatchOrderPath();

        DispatchDetailsDto restaurant = new DispatchDetailsDto();
        // TODO: hit restaurant to get name, address, phone number

        DispatchDetailsDto receiver = new DispatchDetailsDto();
        receiver.setName(order.getDelivery().getName());
        receiver.setAddress(order.getDelivery().getAddress());
        receiver.setPhoneNumber(order.getDelivery().getPhoneNumber());

        DispatchDto req = new DispatchDto();
        req.setOrderId(order.getOrderId());
        req.setTime(order.getDelivery().getTime());
        req.setRestaurant(restaurant);
        req.setReceiver(receiver);

        DispatchDto res = restTemplate.postForObject(url, req, DispatchDto.class);
        log.info("DISPATCH ORDER order:" + req.getOrderId() + ", restaurant:" + restaurant.getName() + ", receiver:" + receiver.getName());

        // TODO: handle unhappy flow

        return res;
    }
}
