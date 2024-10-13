package com.radiogio.models;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class Country {
    private String name;
    private List<State> states;
}
