package com.radiogio.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Builder
@Document(collection = "locations")
public class Location {
    @Id
    private String id;
    private String continent;
    private List<Country> countries;
}
