package com.estatepass.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Visitor {
    private String id;
    private String name;
    private String purposeOfComing;
    private String phoneNumber;

}