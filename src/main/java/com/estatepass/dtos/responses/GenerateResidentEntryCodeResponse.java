package com.estatepass.dtos.responses;

import com.estatepass.data.models.Type;
import lombok.Data;

@Data
public class GenerateResidentEntryCodeResponse {

    private  String code;
    private String residentName;
    private Type codeType;
    private String validTill;
    private String destination;


}
