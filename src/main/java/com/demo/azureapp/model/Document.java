package com.demo.azureapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document
public class Document {

    @Id
    private String id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
