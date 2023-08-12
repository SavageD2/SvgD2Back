package com.svg.D2Back.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScaledStatsDTO {

    private Number statHash;
    private int maximumValue;
    private boolean displayAsNumeric;
    private List<DisplayInterpolationDTO> displayInterpolation;
}
