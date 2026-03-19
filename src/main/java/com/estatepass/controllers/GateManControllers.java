package com.estatepass.controllers;

import com.estatepass.dtos.requests.ValidateCodeRequest;
import com.estatepass.exceptions.InvalidGatePassException;
import com.estatepass.services.GateAccessServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gate-man")

public class GateManControllers {

    @Autowired
    private GateAccessServices gateAccessServices;


    @PostMapping("/validate-code")
    public ResponseEntity<?> validateCode (@RequestBody ValidateCodeRequest request){

            try {
                return ResponseEntity.status(200).body(gateAccessServices.validateCode(request));
            } catch (InvalidGatePassException exception){
                return ResponseEntity.status(400).body(exception.getMessage());
            }
            }



}
