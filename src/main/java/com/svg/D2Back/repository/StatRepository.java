package com.svg.D2Back.repository;

import com.svg.D2Back.entity.DestinyStatDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends JpaRepository<DestinyStatDefinition, Integer> {
}
