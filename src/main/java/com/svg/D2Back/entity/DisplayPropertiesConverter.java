package com.svg.D2Back.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class DisplayPropertiesConverter implements AttributeConverter<DisplayProperties, String> {

    private static final Logger logger = LoggerFactory.getLogger(DisplayPropertiesConverter.class);
    private final ObjectMapper objectMapper ;

    public DisplayPropertiesConverter() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    @Override
    public String convertToDatabaseColumn(DisplayProperties displayProperties) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(displayProperties);
        } catch (JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }
        return jsonString;
    }

    @Override
    public DisplayProperties convertToEntityAttribute(String jsonString) {
        DisplayProperties displayProperties = null;
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode displayPropertiesNode = rootNode.get("displayProperties");

            displayProperties = objectMapper.treeToValue(displayPropertiesNode, DisplayProperties.class);
        } catch (IOException e) {
            logger.error("JSON reading error", e);
        }
        return displayProperties;
    }


}
