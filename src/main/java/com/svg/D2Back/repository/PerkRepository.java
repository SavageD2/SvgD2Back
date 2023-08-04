package com.svg.D2Back.repository;

import com.svg.D2Back.entity.DestinySandboxPerkDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerkRepository extends JpaRepository<DestinySandboxPerkDefinition, Integer> {
}
