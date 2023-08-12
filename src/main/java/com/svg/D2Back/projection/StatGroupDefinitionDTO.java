package com.svg.D2Back.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatGroupDefinitionDTO {

    private List<ScaledStatsDTO> scaledStats;
    private Number statHash;
    private String statName;
    private Integer value;
}
