package com.svg.D2Back.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svg.D2Back.entity.DisplayProperties;
import com.svg.D2Back.entity.Item;
import com.svg.D2Back.entity.StatEntity;
import com.svg.D2Back.entity.Stats;
import com.svg.D2Back.projection.ItemJsonDTO;
import com.svg.D2Back.projection.ItemProjection;
import com.svg.D2Back.projection.ItemProjectionImpl;
import com.svg.D2Back.projection.StatEntityDTO;
import com.svg.D2Back.repository.ItemRepository;
import com.svg.D2Back.repository.StatEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StatEntityRepository statEntityRepository;


    public List<ItemProjection> findByItemNameContaining(String name) {
        name = "%" + name + "%";
        List<Object[]> rawData = itemRepository.findByNameContaining(name);
        return convertToProjection(rawData);
    }

    public List<ItemJsonDTO> findWeapons() {
        List<Object[]> data = itemRepository.findWeaponJsons();
        ObjectMapper objectMapper = new ObjectMapper();

        List<StatEntity> statEntities = statEntityRepository.findAll();
        Map<Integer, StatEntityDTO> statDetails = statEntities.stream()
                .collect(Collectors.toMap(
                        StatEntity::getHash,
                        entity -> {
                            try {
                                return objectMapper.readValue(entity.getJson(), StatEntityDTO.class);
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                ));

        return data.stream()
                .map(entry -> {
                    try {
                        ItemJsonDTO item = objectMapper.readValue((String) entry[1], ItemJsonDTO.class);
                        Stats stats = objectMapper.readValue((String) entry[2], Stats.class);

                        stats.getStats().values().forEach(stat -> {
                           StatEntityDTO details = statDetails.get(stat.getStatHash().intValue());
                           if (details!= null) {
                               stat.setName(details.getDisplayProperties().getName());
                               stat.setDescription(details.getDisplayProperties().getDescription());
                           }
                        });

                        item.setHash((Number) entry[0]);
                        item.setStats(stats);
                        return item;
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<ItemProjection> convertToProjection(List<Object[]> rawData) {
        return rawData.stream().map(data -> {
            Integer hash = (Integer) data[0];
            String name = (String) data[1];
            String description = (String) data[2];
            String icon = (String) data[3];
            Boolean hasIcon = Boolean.parseBoolean((String) data[4]);
            String iconWatermark = (String) data[5];

            DisplayProperties displayProperties = new DisplayProperties(name, description, icon, hasIcon);

            return new ItemProjectionImpl(hash, displayProperties, iconWatermark);
        }).collect(Collectors.toList());
    }

    public ItemJsonDTO getWeaponById(Integer weaponId) {
        return findWeapons().stream()
                .filter(item -> weaponId.equals(item.getHash()))
                .findFirst()
                .orElse(null);
    }
}
