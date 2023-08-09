package com.svg.D2Back.projection;

import com.svg.D2Back.entity.DisplayProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemProjectionImpl implements ItemProjection {
    private Integer hash;
    private DisplayProperties displayProperties;
    private String iconWatermark;
}
