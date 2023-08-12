package com.svg.D2Back.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsDTO {

    private boolean displayPrimaryStatDisplay;
    private Number statGroupHash;
    private Map<String, StatDTO> stats;
    private List<ScaledStatsDTO> scaledStats;
    private boolean hasDisplayableStats;
    private Number primaryBaseStatHash;
}
