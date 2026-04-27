package com.estatepass.dtos.responses;

import lombok.Data;

@Data
public class ViewAllResidentsResponse {
    private String name;
    private String address;
    private String phoneNumber;
    private boolean isEnabled;
    private String dateRegistered;

}
