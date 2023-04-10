package com.soa.fooddelivery.dispatch.controller;

import com.soa.fooddelivery.dispatch.dto.DispatchDto;
import com.soa.fooddelivery.dispatch.service.DispatchService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispatchController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DispatchController.class);
    @Autowired private DispatchService dispatchService;

    @PostMapping("/dispatch")
    public ResponseEntity<DispatchDto> dispatchOrder(@RequestBody DispatchDto request) {
        log.debug("POST /dispatch dispatchOrder");
        DispatchDto response = dispatchService.dispatchOrder(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/dispatch/{dispatchId}/accept")
    public ResponseEntity<DispatchDto> acceptDispatch(@PathVariable(name = "dispatchId") Integer dispatchId) {
        log.debug("POST /dispatch/{dispatchId}/accept acceptDispatch");
        DispatchDto response = dispatchService.acceptDispatch(dispatchId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/dispatch/{dispatchId}/reject")
    public ResponseEntity<DispatchDto> rejectDispatch(@PathVariable(name = "dispatchId") Integer dispatchId) {
        log.debug("POST /dispatch/{dispatchId}/reject rejectDispatch");
        DispatchDto response = dispatchService.rejectDispatch(dispatchId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/dispatch")
    public ResponseEntity<DispatchDto> updateDispatchStatus(@RequestBody DispatchDto request) {
        log.debug("PUT /dispatch updateDispatchStatus");
        DispatchDto response = dispatchService.updateDispatchStatus(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/dispatch/{dispatchId}/status")
    public ResponseEntity<DispatchDto> getDispatchStatus(@PathVariable(name = "dispatchId") Integer dispatchId) {
        log.debug("GET /dispatch/{dispatchId}/status");
        DispatchDto response = dispatchService.getDispatchStatus(dispatchId);
        return ResponseEntity.ok().body(response);
    }
}
