package com.svg.D2Back.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stat {
    private Long statHash;
    private String name;
    private String description;
    private int value;
    private int minimum;
    private int maximum;
    private int displayMaximum;

}
