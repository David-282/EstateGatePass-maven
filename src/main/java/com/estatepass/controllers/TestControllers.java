package com.estatepass.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllers {


    @GetMapping
    public String hello(){
        return "hello world from a springboot";
    }
}
