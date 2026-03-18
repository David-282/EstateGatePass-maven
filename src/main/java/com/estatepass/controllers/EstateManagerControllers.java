package com.estatepass.controllers;

import com.estatepass.dtos.requests.OnboardResidentRequest;
import com.estatepass.services.ResidentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/estate-manager")
public class EstateManagerControllers {

    @Autowired
    private ResidentManagementService residentManagementService;


    @PostMapping("/onboard-resident")
    public ResponseEntity<?> onboardResident (@RequestBody OnboardResidentRequest request){

        return ResponseEntity.status(201).body(residentManagementService.onboardingResident(request));
    }


//        return ResponseEntity.status(201).body(gateAccessServices.generateResidentEntryCode(request));


}
