package com.estatepass.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document
public class GatePass {
    private String id;
    private String residentId;
    private LocalDateTime createdAt =  LocalDateTime.now();
    private LocalDateTime expirationDate;
    private boolean isValid = true;
    private boolean isUsed = false;
    private String code;
    private Visitor visitor;
    private Type passType;

}