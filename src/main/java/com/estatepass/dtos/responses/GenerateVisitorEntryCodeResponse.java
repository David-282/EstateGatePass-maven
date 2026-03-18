package com.estatepass.dtos.responses;

import com.estatepass.data.models.Type;
import lombok.Data;

@Data
public class GenerateVisitorEntryCodeResponse {

    private String code;
    private Type codeType;
    private String visitorName;
    private String validTill;




}
