package com.grupozapvivareal.codechallengeapp.gateways.http.controllers;

import com.grupozapvivareal.codechallengeapp.domain.PropertyWithAllAttributes;
import com.grupozapvivareal.codechallengeapp.gateways.http.json.PropertyJson;
import com.grupozapvivareal.codechallengeapp.services.PropertyService;
import com.grupozapvivareal.codechallengeapp.services.TotalProperties;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    private final ConversionService conversionService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation("${propertycontroller.createproperty}")
    public ResponseEntity<PropertyJson> createProperty(@RequestBody @Valid final PropertyJson propertyJson) {
        final PropertyWithAllAttributes property = propertyService.createProperty(
                propertyJson.getTitle(),
                propertyJson.getPrice(),
                propertyJson.getDescription(),
                propertyJson.getX(),
                propertyJson.getY(),
                propertyJson.getBeds(),
                propertyJson.getBaths(),
                propertyJson.getSquareMeters()
        );
        return ResponseEntity.ok(conversionService.convert(property, PropertyJson.class));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation("${propertycontroller.getpropertybyid}")
    public TotalProperties getPropertyById(int ax, int ay, int bx, int by) {

        TotalProperties foundProperties = new TotalProperties();
        List<PropertyWithAllAttributes> teste = propertyService.searchPropertyByRegion(ax, ay, bx, by);

        foundProperties.setProperties(teste);
        foundProperties.setFoundProperties(teste.size());

        return foundProperties;
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation("${propertycontroller.getpropertybyid}")
    public PropertyWithAllAttributes getPropertyById(@ApiParam("Id of the PropertyJson to be obtained. Cannot be empty.")
                                                     @PathVariable int id) {
        return propertyService.getPropertyById(id);
    }
}
