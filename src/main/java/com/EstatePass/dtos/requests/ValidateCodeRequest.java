package com.EstatePass.dtos.requests;

import com.EstatePass.data.models.Type;
import lombok.Data;

@Data
public class ValidateCodeRequest {

    private String code;
    private Type codeType;


}
