package com.svg.D2Back.repository;

import com.svg.D2Back.entity.StatGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StatGroupRepository extends JpaRepository<StatGroup, Integer> {
}
