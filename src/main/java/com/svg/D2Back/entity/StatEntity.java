package com.svg.D2Back.entity;

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
@Table(name = "DestinyStatDefinition")
public class StatEntity {

    @Id
    @Column(name = "id")
    private Integer hash;

    @Column(name = "json")
    private String json;
}
