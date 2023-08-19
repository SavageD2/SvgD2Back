package com.svg.D2Back.repository;

import com.svg.D2Back.entity.Item;
import com.svg.D2Back.projection.ItemContentProjection;
import com.svg.D2Back.projection.ItemJsonDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT i.id AS hash, " +
            "JSON_EXTRACT(i.json, '$.displayProperties.name') AS name, " +
            "JSON_EXTRACT(i.json, '$.displayProperties.description') AS description, " +
            "JSON_EXTRACT(i.json, '$.displayProperties.icon') AS icon, " +
            "CASE WHEN JSON_EXTRACT(i.json, '$.displayProperties.hasIcon') = 1 THEN 'true' ELSE 'false' END AS hasIcon, " +
            "JSON_EXTRACT(i.json, '$.iconWatermark') AS iconWatermark " +
            "FROM DestinyInventoryItemDefinition i", nativeQuery = true)
    List<Object[]> findAllData();

    @Query(value = "SELECT i.id AS hash, " +
            "JSON_EXTRACT(i.json, '$.displayProperties.name') AS name, " +
            "JSON_EXTRACT(i.json, '$.displayProperties.description') AS description, " +
            "JSON_EXTRACT(i.json, '$.displayProperties.icon') AS icon, " +
            "CASE WHEN JSON_EXTRACT(i.json, '$.displayProperties.hasIcon') = 1 THEN 'true' ELSE 'false' END AS hasIcon, " +
            "JSON_EXTRACT(i.json, '$.iconWatermark') AS iconWatermark " +
            "FROM DestinyInventoryItemDefinition i WHERE JSON_EXTRACT(i.json, '$.displayProperties.name') LIKE :name AND JSON_EXTRACT(i.json, '$.itemType') = 3 ", nativeQuery = true)
    List<Object[]> findByNameContaining(@Param("name") String name); //ma requête pour filtrer les armes par nom


    @Query(value = "SELECT i.id, i.json, JSON_EXTRACT(i.json, '$.stats') as stats FROM DestinyInventoryItemDefinition i WHERE JSON_EXTRACT(i.json, '$.itemType') = 3\n", nativeQuery = true)
    List<Object[]> findWeaponJsons();

    @Query(value = "SELECT i.id, i.json, JSON_EXTRACT(i.json, '$.stats') as stats FROM DestinyInventoryItemDefinition i WHERE i.id = :id AND JSON_EXTRACT(i.json, '$.itemType') = 3", nativeQuery = true)
    Optional<Object[]> findWeaponJsonById(@Param("id") Integer id);

}
