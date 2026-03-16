package com.EstatePass.dtos.responses;

import com.EstatePass.data.models.Type;
import lombok.Data;

@Data
public class GenerateResidentEntryCodeResponse {

    private  String code;
    private String residentName;
    private Type codeType;
    private String validTill;
    private String destination;


}
