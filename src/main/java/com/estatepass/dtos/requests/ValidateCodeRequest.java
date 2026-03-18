package com.estatepass.dtos.requests;

import com.estatepass.data.models.Type;
import lombok.Data;

@Data
public class ValidateCodeRequest {

    private String code;
    private Type codeType;


}
