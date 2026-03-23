package com.estatepass.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document
public class Resident {
    private String name;
    private String id;
    private String phoneNumber;
    private String  houseAddress;
    private LocalDateTime dateRegistered;
    private boolean isEnabled = true;
    private String email;

}