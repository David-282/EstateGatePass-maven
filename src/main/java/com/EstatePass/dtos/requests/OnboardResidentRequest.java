package com.EstatePass.dtos.requests;

import lombok.Data;

@Data
public class OnboardResidentRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;


}
