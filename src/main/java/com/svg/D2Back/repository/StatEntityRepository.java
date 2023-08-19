package com.svg.D2Back.repository;

import com.svg.D2Back.entity.StatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatEntityRepository extends JpaRepository<StatEntity, Integer> {
}
