package com.grupozapvivareal.codechallengeapp.gateways.http.json;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class PropertyJson {

    private String title;

    private BigDecimal price;

    private  String description;

    @Min(0)
    @Max(1400)
    private Integer x;

    @Min(0)
    @Max(1000)
    private Integer y;

    @Min(1)
    @Max(5)
    private Integer beds;

    @Min(1)
    @Max(4)
    private Integer baths;

    @Min(20)
    @Max(240)
    private Integer squareMeters;
}
