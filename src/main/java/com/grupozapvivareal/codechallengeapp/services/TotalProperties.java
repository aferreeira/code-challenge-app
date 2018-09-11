package com.grupozapvivareal.codechallengeapp.services;

import com.grupozapvivareal.codechallengeapp.domain.PropertyWithAllAttributes;
import lombok.Data;

import java.util.List;

@Data
public class TotalProperties {

    private int foundProperties;
    private List<PropertyWithAllAttributes> properties;

}
