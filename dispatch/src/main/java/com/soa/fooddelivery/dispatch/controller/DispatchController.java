package com.soa.fooddelivery.dispatch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispatchController {

    @PostMapping("/dispatch")
    public ResponseEntity<Object> dispatchOrder(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }

    @PostMapping("/dispatch/{dispatchId}/accept")
    public ResponseEntity<Object> acceptDispatch(@PathVariable(name = "dispatchId") String dispatchId) {
        return ResponseEntity.ok().body(new Object());
    }

    @DeleteMapping("/dispatch/{dispatchId}/reject")
    public ResponseEntity<Object> rejectDispatch(@PathVariable(name = "dispatchId") String dispatchId) {
        return ResponseEntity.ok().body(new Object());
    }

    @PutMapping("/dispatch")
    public ResponseEntity<Object> updateDispatchStatus(@RequestBody Object request) {
        return ResponseEntity.ok().body(new Object());
    }
}
