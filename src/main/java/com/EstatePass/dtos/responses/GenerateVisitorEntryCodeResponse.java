package com.EstatePass.dtos.responses;

import com.EstatePass.data.models.Type;
import lombok.Data;

@Data
public class GenerateVisitorEntryCodeResponse {

    private String code;
    private Type codeType;
    private String visitorName;
    private String validTill;




}
