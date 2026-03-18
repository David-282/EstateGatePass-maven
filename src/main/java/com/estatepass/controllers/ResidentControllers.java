package com.estatepass.controllers;

import com.estatepass.dtos.requests.GenerateResidentEntryCodeRequest;
import com.estatepass.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.estatepass.services.GateAccessServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")

public class ResidentControllers {


    @Autowired
    private GateAccessServices gateAccessServices;

    @PostMapping("/generate-resident-code")
    public ResponseEntity<?> generateResidentEntryCode(@RequestBody GenerateResidentEntryCodeRequest request) {
        return ResponseEntity.status(201).body(gateAccessServices.generateResidentEntryCode(request));
    }

}

//@PostMapping
//public ResponseEntity<?> onboardResident(@RequestBody OnboardResidentRequest request) {
//    return ResponseEntity.status(201).body(residentService.onboardingResident(request));
//}