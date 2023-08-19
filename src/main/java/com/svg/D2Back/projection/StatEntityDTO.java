package com.svg.D2Back.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.svg.D2Back.entity.DisplayProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatEntityDTO {
    private DisplayProperties displayProperties;
}
