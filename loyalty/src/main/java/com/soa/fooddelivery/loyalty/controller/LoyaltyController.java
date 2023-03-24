package com.soa.fooddelivery.loyalty.controller;

import com.soa.fooddelivery.loyalty.dto.LoyaltyDto;
import com.soa.fooddelivery.loyalty.dto.PromotionUserDto;
import com.soa.fooddelivery.loyalty.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoyaltyController {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @PostMapping("/loyalty/grant")
    public ResponseEntity<LoyaltyDto> grantLoyalty(@RequestBody LoyaltyDto request){
        LoyaltyDto loyalty = new LoyaltyDto();
        loyalty.setUser(new UserDto("1234", "Juwita", "Pasaribu", "customer", true));
        loyalty.setLoyaltyPoint(loyalty.getLoyaltyPoint()+ request.getLoyaltyPoint());
        return ResponseEntity.ok().body(loyalty);
    }

    @PostMapping("/loyalty/redeem")
    public ResponseEntity<LoyaltyDto> redeemLoyalty(@RequestBody PromotionUserDto request){
        LoyaltyDto loyalty = new LoyaltyDto();
        loyalty.setUser(new UserDto("1234", "Juwita", "Pasaribu", "customer", true));
        RestTemplate restTemplate = restTemplateBuilder.build();
        PromotionUserDto res = restTemplate.postForObject("http://localhost:8085/promotion/grant",request, PromotionUserDto.class);

        loyalty.setLoyaltyPoint(loyalty.getLoyaltyPoint()-res.getPromotion().getLoyaltyPoint());
        return ResponseEntity.ok().body(loyalty);
    }

    @GetMapping("/loyalty/{id}")
    public ResponseEntity<LoyaltyDto> getLoyalty(@PathVariable(name = "userId") String userId){
        LoyaltyDto loyalty = new LoyaltyDto();
        loyalty.setUser(new UserDto(userId, "Juwita", "Pasaribu", "customer", true));
        loyalty.setLoyaltyPoint(24);
        return ResponseEntity.ok().body(loyalty);
    }

}
