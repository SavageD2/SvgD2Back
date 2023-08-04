package com.svg.D2Back.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class DisplayPropertiesConverter implements AttributeConverter<DisplayProperties, String> {

    private static final Logger logger = LoggerFactory.getLogger(DisplayPropertiesConverter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

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
