package com.soa.fooddelivery.loyalty.controller;

import com.soa.fooddelivery.loyalty.dto.LoyaltyDto;
import com.soa.fooddelivery.loyalty.service.LoyaltyService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoyaltyController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoyaltyController.class);
    @Autowired private LoyaltyService loyaltyService;

    @PostMapping("/loyalty/grant")
    public ResponseEntity<LoyaltyDto> grantLoyalty(@RequestBody LoyaltyDto request){
        log.debug("POST /loyalty/grant grantLoyalty");
        LoyaltyDto response = loyaltyService.grantLoyalty(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/loyalty/redeem")
    public ResponseEntity<LoyaltyDto> redeemLoyalty(@RequestBody LoyaltyDto request){
        log.debug("POST /loyalty/redeem redeemLoyalty");
        LoyaltyDto response = loyaltyService.redeemLoyalty(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/loyalty/{userId}")
    public ResponseEntity<LoyaltyDto> getLoyalty(@PathVariable(name = "userId") String userId){
        log.debug("GET /loyalty/{userId} getLoyalty");
        LoyaltyDto response = loyaltyService.getLoyalty(userId);
        return ResponseEntity.ok().body(response);
    }

}
