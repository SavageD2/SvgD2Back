package com.svg.D2Back.repository;

import com.svg.D2Back.entity.Item;
import com.svg.D2Back.projection.ItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("SELECT i.hash as hash, i.displayProperties as displayProperties FROM Item i")
    List<ItemProjection> findItemsWithSelectedColumns();

    Optional<ItemProjection> findByHash(Integer hash);


}
