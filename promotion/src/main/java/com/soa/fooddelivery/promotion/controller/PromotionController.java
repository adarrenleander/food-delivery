package com.soa.fooddelivery.promotion.controller;

import com.soa.fooddelivery.promotion.dto.PromotionDto;
import com.soa.fooddelivery.promotion.dto.PromotionUserDto;
import com.soa.fooddelivery.promotion.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PromotionController {


    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @GetMapping("/promotion/eligibility")
    public ResponseEntity<Object> checkPromotionEligibility(@RequestBody PromotionUserDto request) {
        Map<String, Object> result = new HashMap<String, Object>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        //TODO : check if the promotionUser exist in db,
        //
        // TODO: check if the user active
        UserDto res = restTemplate.getForObject("http://localhost:8088/user/"+request.getUser().getId(), UserDto.class);
        if (res == null){
            result.put("eligibility", false);
            result.put("finalAmount", request.getTotalAmount());
            return ResponseEntity.ok().body(result);
        }
        //TODO :check if the promotion status is active

        DecimalFormat f = new DecimalFormat("##.00");

        result.put("eligibility", request.getPromotion().getActiveStatus());
        result.put("finalAmount", f.format(request.getTotalAmount()-(request.getTotalAmount()*request.getPromotion().getDiscount()*0.01)));
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/promotion")
    public ResponseEntity<PromotionDto> createPromotion(@RequestBody PromotionDto request){
        PromotionDto promotion = new PromotionDto();
        promotion.setId(request.getId());
        promotion.setCode(request.getCode());
        promotion.setDiscount(request.getDiscount());
        promotion.setActiveStatus(true);
        return ResponseEntity.ok().body(promotion);
    }

    @PutMapping("/promotion")
    public ResponseEntity<PromotionDto> updatePromotion(@RequestBody PromotionDto request) {
        PromotionDto response = new PromotionDto();
        //TODO: get promotion where id=request.getId();
        response.setId(request.getId()); //not needed if the data already taken by db
        response.setCode(request.getCode());
        response.setDiscount(request.getDiscount());
        response.setActiveStatus(true);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/promotion/{id}")
    public ResponseEntity<UserDto> deletePromotion(@PathVariable(name = "id") String id) {
        UserDto response = new UserDto();
        response.setId(id);
        response.setActiveStatus(false);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/promotion")
    public ResponseEntity<List<PromotionDto>> getAllPromotion(){
        //TODO: filter only the active one and userId
        List<PromotionDto> response = getSampleFullPromotion();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/promotion/{id}")
    public ResponseEntity<PromotionDto> getMenuItemById(@PathVariable(name = "id") String id){
        List<PromotionDto> promotions = getSampleFullPromotion();
        PromotionDto response = new PromotionDto();
        for (PromotionDto promotion : promotions){
            if (promotion.getId().equals(id)){
                response = promotion;
            }
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/promotion/grant")
    public ResponseEntity<PromotionUserDto> grantPromotion(@RequestBody PromotionUserDto request){
        PromotionUserDto promotionUser = new PromotionUserDto();
        promotionUser.setPromotion(new PromotionDto("123","ABC123",(float)30, true,14));
        promotionUser.setUser(new UserDto("1234", "Juwita", "Pasaribu", "customer", true));
        promotionUser.setActiveStatus(true);
        return ResponseEntity.ok().body(promotionUser);
    }

    @PostMapping("/promotion/apply")
    public ResponseEntity<PromotionUserDto> applyPromotion(@RequestBody PromotionUserDto request){
        PromotionUserDto promotionUser = new PromotionUserDto();
        promotionUser.setPromotion(new PromotionDto("123","ABC123",(float)30, true,14));
        promotionUser.setUser(new UserDto("1234", "Juwita", "Pasaribu", "customer", true));
        promotionUser.setActiveStatus(false);
        return ResponseEntity.ok().body(promotionUser);
    }

    public List<PromotionDto> getSampleFullPromotion() {
        List<PromotionDto> response = new ArrayList<>();
        PromotionDto promotion1 = new PromotionDto("123","ABC123",(float)30, true,12);
        PromotionDto promotion2 = new PromotionDto("124","ABC1234",(float)40, true,13);
        PromotionDto promotion3 = new PromotionDto("125","ABC12345",(float)60, true,20);
        response.add(promotion1);
        response.add(promotion2);
        response.add(promotion3);
        return response;
    }

}
