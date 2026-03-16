package com.EstatePass.dtos.responses;

import com.EstatePass.data.models.Type;
import lombok.Data;

@Data
public class ValidateCodeResponse {
    private String residentName;
    private String visitorsName;
    private Type codeType;
    private boolean isValid;


}
