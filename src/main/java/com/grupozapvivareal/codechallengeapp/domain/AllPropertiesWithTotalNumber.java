package com.grupozapvivareal.codechallengeapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllPropertiesWithTotalNumber {

    private int totalProperties;
    private List<PropertyWithAllAttributes> properties;

}

