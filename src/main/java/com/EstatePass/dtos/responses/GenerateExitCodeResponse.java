package com.EstatePass.dtos.responses;

import com.EstatePass.data.models.Type;
import lombok.Data;

@Data
public class GenerateExitCodeResponse {

    private String code;
    private  String name;
    private Type codeType;
    private String validTill;
    private String houseAddress;


}
