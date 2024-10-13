package com.radiogio.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "radios")
public class Radio {
    @Id
    private String id;
    private String name;
    private String description;
    private String url;
    private String imageUrl;
    private String country;
    private String state;
    private String continent;
}
