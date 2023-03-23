package com.soa.fooddelivery.dispatch.controller;

import com.soa.fooddelivery.dispatch.dto.DispatchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispatchController {

    @PostMapping("/dispatch")
    public ResponseEntity<DispatchDto> dispatchOrder(@RequestBody DispatchDto request) {
        DispatchDto response = new DispatchDto();
        response.setDispatchId("xxx");
        response.setStatus("created");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/dispatch/{dispatchId}/accept")
    public ResponseEntity<DispatchDto> acceptDispatch(@PathVariable(name = "dispatchId") String dispatchId) {
        DispatchDto response = new DispatchDto();
        response.setDispatchId(dispatchId);
        response.setStatus("accepted");
        response.setDriverId("xxx");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/dispatch/{dispatchId}/reject")
    public ResponseEntity<DispatchDto> rejectDispatch(@PathVariable(name = "dispatchId") String dispatchId) {
        DispatchDto response = new DispatchDto();
        response.setDispatchId(dispatchId);
        response.setStatus("finding driver");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/dispatch")
    public ResponseEntity<DispatchDto> updateDispatchStatus(@RequestBody DispatchDto request) {
        DispatchDto response = new DispatchDto();
        response.setDispatchId(request.getDispatchId());
        response.setStatus(request.getStatus());
        return ResponseEntity.ok().body(response);
    }
}
