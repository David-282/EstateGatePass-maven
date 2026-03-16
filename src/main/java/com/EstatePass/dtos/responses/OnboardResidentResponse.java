package com.EstatePass.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OnboardResidentResponse {

    private String residentName;
    private String residentId;
    private LocalDateTime dateRegistered;



}
