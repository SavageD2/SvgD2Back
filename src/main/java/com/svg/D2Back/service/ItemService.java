package com.svg.D2Back.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svg.D2Back.entity.DisplayProperties;

import com.svg.D2Back.entity.Item;
import com.svg.D2Back.entity.StatGroup;
import com.svg.D2Back.entity.Stats;
import com.svg.D2Back.projection.*;
import com.svg.D2Back.repository.ItemRepository;
import com.svg.D2Back.repository.StatGroupRepository;
import com.svg.D2Back.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StatsRepository statsRepository;
    @Autowired
    private StatGroupRepository statGroupRepository;

    @Autowired
    ObjectMapper objectMapper;

    public List<ItemProjection> findByItemNameContaining(String name) {
        name = "%" + name + "%";
        List<Object[]> rawData = itemRepository.findByNameContaining(name);
        return convertToProjection(rawData);
    }

    public List<ItemJsonDTO> findWeapons() {
        List<Object[]> data = itemRepository.findWeaponJsons();
        return data.stream()
                .map(entry -> {
                    try {
                        ItemJsonDTO item = objectMapper.readValue((String) entry[1], ItemJsonDTO.class);
                        item.setHash((Number) entry[0]);
                        return item;
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error processing JSON for weapon", e);
                    }
                })
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

    public ItemDetailsDTO getItemDetails(Integer itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);
        if(item == null) {
            return null;
        }

        ItemJsonDTO itemData = parseJson(item.getJsonContent(), ItemJsonDTO.class);

        if(itemData.getStats() != null && itemData.getStats().getStats() != null) {
            for(Map.Entry<String, StatDTO> entry : itemData.getStats().getStats().entrySet()) {
                StatDTO statDto = entry.getValue();
                Number statHash = statDto.getStatHash();

                Optional<Stats> optionalStatDefinition = statsRepository.findById(statHash.intValue());
                if(optionalStatDefinition.isPresent()) {
                    StatDefinitionDTO statDefinition = parseJson(
                            optionalStatDefinition.get().getJsonContent(),
                            StatDefinitionDTO.class
                    );

                    if(statDefinition != null && statDefinition.getDisplayProperties() != null) {
                        statDto.setStatName(statDefinition.getDisplayProperties().getName());
                    }
                }
            }
        }
        List<StatDetailDTO> detailedStats = new ArrayList<>();
        if(itemData.getStats() != null && itemData.getStats().getStats() != null) {
            for(Map.Entry<String, StatDTO> entry : itemData.getStats().getStats().entrySet()) {
                StatDTO statDto = entry.getValue();
                Number statHash = statDto.getStatHash();

                Optional<Stats> optionalStatDefinition = statsRepository.findById(statHash.intValue());
                if(optionalStatDefinition.isPresent()) {
                    StatDefinitionDTO statDefinition = parseJson(
                            optionalStatDefinition.get().getJsonContent(),
                            StatDefinitionDTO.class
                    );

                    StatDetailDTO statDetail = new StatDetailDTO();
                    if(statDefinition != null && statDefinition.getDisplayProperties() != null) {
                        statDetail.setName(statDefinition.getDisplayProperties().getName());
                        statDetail.setDescription(statDefinition.getDisplayProperties().getDescription());
                        detailedStats.add(statDetail);
                    }
                }
            }
            StatGroup statGroup = statGroupRepository.findById(itemData.getStats().getStatGroupHash().intValue()).orElse(null);
            if(statGroup != null) {
                StatGroupDefinitionDTO statGroupDef = parseJson(statGroup.getJsonContent(), StatGroupDefinitionDTO.class);

                if(statGroupDef != null && statGroupDef.getScaledStats() != null) {
                    List<ScaledStatsDTO> scaledStats = statGroupDef.getScaledStats();
                    itemData.getStats().setScaledStats(scaledStats);
                }
            }
            if (itemData.getStats() != null && itemData.getStats().getStats() != null) {

                StatGroup statsGroup = statGroupRepository.findById(itemData.getStats().getStatGroupHash().intValue()).orElse(null);
                if (statsGroup != null) {
                    StatGroupDefinitionDTO statGroupDef = parseJson(statGroup.getJsonContent(), StatGroupDefinitionDTO.class);

                    for (Map.Entry<String, StatDTO> entry : itemData.getStats().getStats().entrySet()) {
                        StatDTO statDto = entry.getValue();
                        Number statHash = statDto.getStatHash();

                        for (ScaledStatsDTO scaledStatsDto : statGroupDef.getScaledStats()) {
                            if (scaledStatsDto.getStatHash().equals(statHash.intValue())) {

                                statDto.setScaledStats(scaledStatsDto);
                                break;
                            }
                        }
                    }
                }
            }

        }

        ItemDetailsDTO itemDetails = new ItemDetailsDTO();
        itemDetails.setItemData(itemData);
        itemDetails.setStats(detailedStats);

        return itemDetails;
    }


    private <T> T parseJson(String jsonContent, Class<T> type) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonContent, type);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
