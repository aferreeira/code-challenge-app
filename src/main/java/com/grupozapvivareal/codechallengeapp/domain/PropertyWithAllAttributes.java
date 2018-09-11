package com.grupozapvivareal.codechallengeapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyWithAllAttributes {

    private Integer id;
    private String title;
    private Integer price;
    private String description;
    private Integer lat;

    @JsonProperty(value = "long")
    private Integer longx;

    private Integer beds;
    private Integer baths;
    private List<String> provinces;
    private Integer squareMeters;

}
