package com.svg.D2Back.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.svg.D2Back.projection.ItemProjection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DestinyInventoryItemDefinition")
public class Item {

    @Id
    @Column(name = "id")
    private Integer hash;

    @Column(name = "json", columnDefinition = "TEXT")
    private String jsonContent;
}
