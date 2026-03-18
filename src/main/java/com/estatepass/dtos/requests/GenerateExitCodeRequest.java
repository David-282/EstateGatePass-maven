package com.estatepass.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GenerateExitCodeRequest {
    private String code;
    private LocalDateTime validTill;

}
