package com.soa.fooddelivery.promotion.controller;

import com.soa.fooddelivery.promotion.dto.PromotionDto;
import com.soa.fooddelivery.promotion.dto.PromotionUserDto;
import com.soa.fooddelivery.promotion.dto.UserDto;
import com.soa.fooddelivery.promotion.entity.PromotionUser;
import com.soa.fooddelivery.promotion.repository.PromotionRepository;
import com.soa.fooddelivery.promotion.repository.PromotionUserRepository;
import com.soa.fooddelivery.promotion.service.PromotionService;
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
    PromotionUserRepository promotionUserRepository;

    @Autowired
    PromotionService promotionService;

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @PostMapping("/promotion/eligibility")
    public ResponseEntity<Object> checkPromotionEligibility(@RequestBody PromotionUserDto request) {
        Map<String, Object> result = new HashMap<String, Object>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<PromotionUser> promotionUser = promotionUserRepository.getPromotionUserByPromotionIdAndUserId(request.getPromotion().getId(),request.getUser().getId());
        if (promotionUser.size()>0){
            if (promotionUser.get(0).getUsageStatus()!=null && promotionUser.get(0).getPromotion().getActiveStatus()) {
                if (!promotionUser.get(0).getUsageStatus().equals("success")) {
                    UserDto res = restTemplate.getForObject("http://localhost:8088/user/"+request.getUser().getId(), UserDto.class);
                    if (res != null){
                        DecimalFormat f = new DecimalFormat("##.00");
                        request.getPromotion().setDiscount(10F);
                        request.getPromotion().setActiveStatus(true);
                        result.put("eligibility", request.getPromotion().getActiveStatus());
                        result.put("finalAmount", f.format(request.getTotalAmount()-(request.getTotalAmount()*request.getPromotion().getDiscount()*0.01)));
                        return ResponseEntity.ok().body(result);
                    }
                }
            }

        }
            result.put("eligibility", false);
            result.put("finalAmount", request.getTotalAmount());
            return ResponseEntity.ok().body(result);

    }

    @PostMapping("/promotion")
    public ResponseEntity<PromotionDto> createPromotion(@RequestBody PromotionDto request){
        PromotionDto promotion = promotionService.createPromotion(request);
        return ResponseEntity.ok().body(promotion);
    }

    @PutMapping("/promotion")
    public ResponseEntity<PromotionDto> updatePromotion(@RequestBody PromotionDto request) {
        PromotionDto response = promotionService.updatePromotion(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/promotion/{id}")
    public ResponseEntity<PromotionDto> deletePromotion(@PathVariable(name = "id") Integer id) {
        PromotionDto response = promotionService.deletePromotion(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/promotion/{userId}")
    public ResponseEntity<List<PromotionDto>> getAllPromotion(@PathVariable(name = "userId") Integer userId){
        List<PromotionDto> response = promotionUserRepository.getPromotionByUserIdOrderByDiscount(userId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/promotion/{code}")
    public ResponseEntity<PromotionDto> getPromotionByCode(@PathVariable(name = "code") String code){
        PromotionDto response = promotionRepository.getPromotionByCode(code);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/promotion/grant")
    public ResponseEntity<PromotionUserDto> grantPromotion(@RequestBody PromotionUserDto request){
        PromotionUserDto promotionUser = promotionService.grantPromotion(request);
        return ResponseEntity.ok().body(promotionUser);
    }

    @PostMapping("/promotion/apply")
    public ResponseEntity<PromotionUserDto> applyPromotion(@RequestBody PromotionUserDto request){
        PromotionUserDto promotionUser = promotionService.applyPromotion(request);
        return ResponseEntity.ok().body(promotionUser);
    }

    /*public List<PromotionDto> getSampleFullPromotion() {
        List<PromotionDto> response = new ArrayList<>();
        PromotionDto promotion1 = new PromotionDto(123,"ABC123",(float)30, true,12);
        PromotionDto promotion2 = new PromotionDto(124,"ABC1234",(float)40, true,13);
        PromotionDto promotion3 = new PromotionDto(125,"ABC12345",(float)60, true,20);
        response.add(promotion1);
        response.add(promotion2);
        response.add(promotion3);
        return response;
    }

     */

}
