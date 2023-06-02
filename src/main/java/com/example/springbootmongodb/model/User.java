package com.example.springbootmongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Data
@Document
public class User {
    @Id
    private String userId;
    private String name;
    private String position;
    private Date creationDate = new Date();
    private Map<String, String> userSettings;
}
