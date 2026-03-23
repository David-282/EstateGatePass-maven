package com.estatepass.dtos.responses;

import lombok.Data;
@Data
public class ViewAllGatePassesResponse {

    private String code;
    private String residentName;
    private String visitorsName;
    private String purposeOfComing;
    private String visitorsPhoneNumber;
    private String residentPhoneNumber;
    private String residentAddress;
}
