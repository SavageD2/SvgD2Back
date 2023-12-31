package com.svg.D2Back.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.svg.D2Back.entity.DisplayProperties;
import com.svg.D2Back.entity.Stat;
import com.svg.D2Back.entity.Stats;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemJsonDTO {
    private Number hash;
    private DisplayProperties displayProperties;
    private String iconWatermark;
    private Stats stats;
}
