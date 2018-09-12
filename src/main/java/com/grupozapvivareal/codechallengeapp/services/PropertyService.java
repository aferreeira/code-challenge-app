package com.grupozapvivareal.codechallengeapp.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupozapvivareal.codechallengeapp.domain.AllPropertiesWithTotalNumber;
import com.grupozapvivareal.codechallengeapp.domain.PropertyWithAllAttributes;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

@Service
public class PropertyService {

    private int totalProperties;
    private List<PropertyWithAllAttributes> allProperties;

    @PostConstruct
    void init() throws IOException {

        this.allProperties = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        URL propertiesJson = new URL("https://raw.githubusercontent.com/grupozap/code-challenge/master/properties.json");
        URLConnection connection = propertiesJson.openConnection();
        BufferedReader propertiesJsonBuffered = new BufferedReader( new InputStreamReader(connection.getInputStream(),StandardCharsets.UTF_8.name()));

        AllPropertiesWithTotalNumber properties = objectMapper.readValue(propertiesJsonBuffered, AllPropertiesWithTotalNumber.class);

        this.totalProperties = properties.getTotalProperties();

        for (PropertyWithAllAttributes imovel : properties.getProperties()) {

            List<String> provinces = listaString(imovel.getLat(), imovel.getLongx());
            imovel.setProvinces(provinces);
            allProperties.add(imovel);

        }

    }

    private List<String> listaString(int x, int y) {

        List<String> provinces = new ArrayList<>();

        if ((x >= 0) && (x <= 600) && (y >= 500) && (y <= 1000)) {
            provinces.add("Gode");
        }
        if ((x >= 400) && (x <= 1100) && (y >= 500) && (y <= 1000)) {
            provinces.add("Ruja");
        }
        if ((x >= 1100) && (x <= 1400) && (y >= 500) && (y <= 1000)) {
            provinces.add("Jaby");
        }
        if ((x >= 0) && (x <= 600) && (y >= 0) && (y <= 500)) {
            provinces.add("Scavy");
        }
        if ((x >= 600) && (x <= 800) && (y >= 0) && (y <= 500)) {
            provinces.add("Groola");
        }
        if ((x >= 800) && (x <= 1400) && (y >= 0) && (y <= 500)) {
            provinces.add("Nova");
        }

        return provinces;
    }

    public PropertyWithAllAttributes getPropertyById(int id) {
        return this.allProperties
                .stream()
                .filter(property -> property.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public PropertyWithAllAttributes createProperty(String title, BigDecimal price, String description, int x, int y, int beds, int baths, int squareMeters) {

        int id = totalProperties + 1;
        this.totalProperties++;

        List<String> provinces = listaString(x, y);

        allProperties.add(new PropertyWithAllAttributes(id, title, price, description, x, y, beds, baths, provinces, squareMeters));

        return null;
    }

    public List<PropertyWithAllAttributes> searchPropertyByRegion(int ax, int ay, int bx, int by) {

        List<PropertyWithAllAttributes> properties;
        properties = new ArrayList<>();

        for (PropertyWithAllAttributes property : this.allProperties) {
            if ((property.getLat() >= ax) && (property.getLat() <= bx) && (property.getLongx() >= ay) && (property.getLongx() <= by)) {
                properties.add(property);
            }
        }

        return properties;

    }


}