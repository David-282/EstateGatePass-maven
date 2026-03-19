package com.estatepass.controllers;

import com.estatepass.dtos.requests.OnboardResidentRequest;
import com.estatepass.exceptions.ResidentAlreadyRegisteredException;
import com.estatepass.exceptions.ResidentDoesNotExistException;
import com.estatepass.services.GateAccessServices;
import com.estatepass.services.ResidentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estate-manager")
public class EstateManagerControllers {

    @Autowired
    private ResidentManagementService residentManagementService;

    @Autowired
    private GateAccessServices gateAccessServices;


    @PostMapping("/onboard-resident")
    public ResponseEntity<?> onboardResident (@RequestBody OnboardResidentRequest request) {

        try {
            return ResponseEntity.status(201).body(residentManagementService.onboardingResident(request));
        }catch (ResidentAlreadyRegisteredException exception){
            return ResponseEntity.status(400).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete-resident/{phoneNumber}")
    public ResponseEntity <?> deleteResident (@PathVariable String phoneNumber){
        try{
        return ResponseEntity.status(200).body(residentManagementService.deleteResident(phoneNumber));}
        catch (ResidentDoesNotExistException exception){
            return ResponseEntity.status(404).body(exception.getMessage());
        }
    }
  
    @PatchMapping("/disable-resident/{phoneNumber}")
    public ResponseEntity<?> disableResident (@PathVariable String phoneNumber){
        try {
            return ResponseEntity.status(200).body(residentManagementService.disableResident(phoneNumber));
        } catch (ResidentDoesNotExistException exception) {
            return ResponseEntity.status(404).body(exception.getMessage());
        }
    }

    @GetMapping("/view-residents")
    public ResponseEntity<?> viewResidents() {
        return ResponseEntity.status(200).body(residentManagementService.viewResident());
    }

    @GetMapping("/view-all-gate-passes")
    public ResponseEntity<?> viewAllGatePasses() {
        return ResponseEntity.status(200).body(gateAccessServices.viewAllGatePasses());
    }
}
