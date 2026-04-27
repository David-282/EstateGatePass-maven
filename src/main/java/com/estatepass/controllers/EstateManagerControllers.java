package com.estatepass.controllers;

import com.estatepass.dtos.requests.OnboardResidentRequest;
import com.estatepass.exceptions.ResidentAlreadyRegisteredException;
import com.estatepass.exceptions.ResidentDisabledException;
import com.estatepass.exceptions.ResidentDoesNotExistException;
import com.estatepass.exceptions.ResidentEnabledException;
import com.estatepass.services.GateAccessServices;
import com.estatepass.services.ResidentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  
//    @PatchMapping("/disable-resident/{phoneNumber}")
//    public ResponseEntity<?> disableResident (@PathVariable String phoneNumber){
//        try {
//            return ResponseEntity.status(200).body(residentManagementService.disableResident(phoneNumber));
//        } catch (ResidentDoesNotExistException exception) {
//            return ResponseEntity.status(404).body(exception.getMessage());
//        }
//    } catch (ResidentDisabledException exception) {
//        return ResponseEntity.status(400).body(exception.getMessage());
//    }
//    }
@PatchMapping("/disable-resident/{phoneNumber}")
public ResponseEntity<?> disableResident(@PathVariable String phoneNumber) {
    try {
        var response = residentManagementService.disableResident(phoneNumber);
        return ResponseEntity.ok(response);
    } catch (ResidentDoesNotExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (ResidentDisabledException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}

    @PatchMapping("/enable-resident/{phoneNumber}")
    public ResponseEntity<?> enableResident(@PathVariable String phoneNumber) {
        try {
            return ResponseEntity.ok(residentManagementService.enableResident(phoneNumber));
        } catch (ResidentDoesNotExistException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (ResidentEnabledException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/view-residents")
    public ResponseEntity<?> viewResidents() {
        return ResponseEntity.status(200).body(residentManagementService.viewResidents());
    }

    @GetMapping("/view-all-gate-passes")
    public ResponseEntity<?> viewAllGatePasses() {
        return ResponseEntity.status(200).body(gateAccessServices.viewAllGatePasses());
    }
}
