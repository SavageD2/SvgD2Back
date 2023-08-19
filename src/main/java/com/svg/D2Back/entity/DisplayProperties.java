package com.svg.D2Back.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisplayProperties {


    private String description;

    private String name;

    private String icon;

    private boolean hasIcon;
    public DisplayProperties(String name, String description, String icon, boolean hasIcon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.hasIcon = hasIcon;
    }

}
