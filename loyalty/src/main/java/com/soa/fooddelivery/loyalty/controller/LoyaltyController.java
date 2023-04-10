package com.soa.fooddelivery.loyalty.controller;

import com.soa.fooddelivery.loyalty.dto.LoyaltyDto;
import com.soa.fooddelivery.loyalty.repository.LoyaltyRepository;
import com.soa.fooddelivery.loyalty.service.LoyaltyService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoyaltyController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoyaltyController.class);
    @Autowired
    LoyaltyService loyaltyService;
    @Autowired
    LoyaltyRepository loyaltyRepository;

    @PostMapping("/loyalty")
    public ResponseEntity<LoyaltyDto> createUser(@RequestBody LoyaltyDto request) {
        LoyaltyDto response = loyaltyService.createLoyalty(request);
        return ResponseEntity.ok().body(response);
    }

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
    public ResponseEntity<LoyaltyDto> getLoyalty(@PathVariable(name = "userId") Integer userId){
        log.debug("GET /loyalty/{userId} getLoyalty");
        LoyaltyDto response = loyaltyRepository.findAllByUserId(userId).get(0).convertToDto();
        return ResponseEntity.ok().body(response);
    }

}
