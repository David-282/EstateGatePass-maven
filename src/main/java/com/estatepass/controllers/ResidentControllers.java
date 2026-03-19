package com.estatepass.controllers;

import com.estatepass.dtos.requests.ExtendCodeRequest;
import com.estatepass.dtos.requests.GenerateExitCodeRequest;
import com.estatepass.dtos.requests.GenerateResidentEntryCodeRequest;
import com.estatepass.dtos.requests.GenerateVisitorEntryCodeRequest;
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
        return ResponseEntity.status(201).body(gateAccessServices.generateResidentEntryCode(request));
    }

    @PatchMapping("/disable-code")
    public ResponseEntity<?> disableCode (@RequestParam String code){
        return ResponseEntity.status(200).body(gateAccessServices.disableCode(code));
    }

    @PostMapping("/generate-visitor-entry-code")
    public ResponseEntity<?> generateVisitorEntryCode(@RequestBody GenerateVisitorEntryCodeRequest request){

        return ResponseEntity.status(201).body(gateAccessServices.generateVisitorsEntryCode(request));
    }

    @PostMapping("/generate-exit-code")
    public ResponseEntity<?> generateExitCode(@RequestBody GenerateExitCodeRequest request){

        return ResponseEntity.status(201).body(gateAccessServices.generateExitCode(request));
    }

    @PostMapping("/extend-code")
    public ResponseEntity<?> extendCode (@RequestBody ExtendCodeRequest request){

        return ResponseEntity.status(201).body(gateAccessServices.extendCode(request));
    }


}