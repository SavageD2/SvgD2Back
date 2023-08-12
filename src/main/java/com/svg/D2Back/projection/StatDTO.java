package com.svg.D2Back.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatDTO {
    private Number statHash;
    private String statName;
    private ScaledStatsDTO scaledStats;
}
