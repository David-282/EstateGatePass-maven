package com.estatepass.controllers;

import com.estatepass.dtos.requests.ExtendCodeRequest;
import com.estatepass.dtos.requests.GenerateExitCodeRequest;
import com.estatepass.dtos.requests.GenerateResidentEntryCodeRequest;
import com.estatepass.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.estatepass.exceptions.InvalidGatePassException;
import com.estatepass.exceptions.ResidentDisabledException;
import com.estatepass.exceptions.ResidentDoesNotExistException;
import com.estatepass.services.GateAccessServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents")

public class ResidentControllers {


    @Autowired
    private GateAccessServices gateAccessServices;

    @PostMapping("/generate-resident-code")
    public ResponseEntity<?> generateResidentEntryCode(@RequestBody GenerateResidentEntryCodeRequest request) {
        try {
            return ResponseEntity.status(201).body(gateAccessServices.generateResidentEntryCode(request));
        } catch (ResidentDoesNotExistException exception) {
            return ResponseEntity.status(404).body(exception.getMessage());
        } catch (ResidentDisabledException exception) {
            return ResponseEntity.status(400).body(exception.getMessage());
        }
    }
    @PatchMapping("/disable-code")
    public ResponseEntity<?> disableCode (@RequestParam String code){
        try {
            return ResponseEntity.status(200).body(gateAccessServices.disableCode(code));
        }catch (InvalidGatePassException exception){
            return ResponseEntity.status(404).body(exception.getMessage());

        }
    }

    @PostMapping("/generate-visitor-entry-code")
    public ResponseEntity<?> generateVisitorEntryCode(@RequestBody GenerateVisitorEntryCodeRequest request){
        try {
        return ResponseEntity.status(201).body(gateAccessServices.generateVisitorsEntryCode(request));
        } catch (ResidentDoesNotExistException exception) {
        return ResponseEntity.status(404).body(exception.getMessage());
    } catch (ResidentDisabledException exception) {
        return ResponseEntity.status(400).body(exception.getMessage());
    }
    }

    @PostMapping("/generate-exit-code")
    public ResponseEntity<?> generateExitCode(@RequestBody GenerateExitCodeRequest request){
        try {
            return ResponseEntity.status(201).body(gateAccessServices.generateExitCode(request));
        } catch (InvalidGatePassException exception){
            return ResponseEntity.status(400).body(exception.getMessage());
        }
    }

    @PostMapping("/extend-code")
    public ResponseEntity<?> extendCode (@RequestBody ExtendCodeRequest request){
        try{
        return ResponseEntity.status(201).body(gateAccessServices.extendCode(request));
    } catch (InvalidGatePassException exception) {
            return ResponseEntity.status(400).body(exception.getMessage());
        }
        }


}