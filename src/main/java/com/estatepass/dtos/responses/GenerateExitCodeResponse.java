package com.estatepass.dtos.responses;

import com.estatepass.data.models.Type;
import lombok.Data;

@Data
public class GenerateExitCodeResponse {

    private String code;
    private  String name;
    private Type codeType;
    private String validTill;
    private String houseAddress;


}
