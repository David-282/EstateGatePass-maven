package com.EstatePass.dtos.requests;

import lombok.Data;

@Data
public class ExtendCodeRequest {

    private String code;
    private int hoursToExtendBy;


}
