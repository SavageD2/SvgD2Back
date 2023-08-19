package com.svg.D2Back.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {
    private boolean disablePrimaryStatDisplay;
    private long statGroupHash;
    private Map<String, Stat> stats;
    private boolean hasDisplayableStats;
    private long primaryBaseStatHash;
}
