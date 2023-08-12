package com.svg.D2Back.entity;

import com.svg.D2Back.projection.StatGroupDefinitionDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DestinyStatGroupDefinition")
public class StatGroup {

    @Id
    @Column(name = "id")
    private Integer hash;

    @Column(name = "json")
    private String jsonContent;
}
