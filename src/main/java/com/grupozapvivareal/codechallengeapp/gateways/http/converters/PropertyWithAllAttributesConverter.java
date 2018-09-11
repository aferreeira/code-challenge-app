package com.grupozapvivareal.codechallengeapp.gateways.http.converters;

import com.grupozapvivareal.codechallengeapp.domain.PropertyWithAllAttributes;
import com.grupozapvivareal.codechallengeapp.gateways.http.json.PropertyJson;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PropertyWithAllAttributesConverter implements Converter<PropertyWithAllAttributes, PropertyJson> {

    @Override
    public PropertyJson convert(PropertyWithAllAttributes source) {
        final PropertyJson PropertyJson = new PropertyJson();
        BeanUtils.copyProperties(source, PropertyJson);
        return PropertyJson;
    }
}
