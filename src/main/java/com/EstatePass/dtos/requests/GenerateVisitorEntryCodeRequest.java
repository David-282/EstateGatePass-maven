package com.EstatePass.dtos.requests;

import lombok.Data;

@Data
public class GenerateVisitorEntryCodeRequest {

    private String visitorName;
    private String phoneNumber;
    private String residentId;
    private  int validHour;
    private String purposeOfVisit;




}
