package com.svg.D2Back.controller;

import com.svg.D2Back.projection.ItemDetailsDTO;
import com.svg.D2Back.projection.ItemJsonDTO;
import com.svg.D2Back.projection.ItemProjection;
import com.svg.D2Back.repository.ItemRepository;
import com.svg.D2Back.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public ResponseEntity<List<ItemProjection>> getItems() {
        List<Object[]> rawData = itemRepository.findAllData();
        List<ItemProjection> projections = itemService.convertToProjection(rawData);

        return new ResponseEntity<>(projections, HttpStatus.OK);
    }

    @GetMapping("/w")
    public List<ItemJsonDTO> getWeapons() {
        return itemService.findWeapons();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemProjection>> getItemsByName(@RequestParam String name) {
        List<ItemProjection> projections = itemService.findByItemNameContaining(name);
        return new ResponseEntity<>(projections, HttpStatus.OK);
    }

    @GetMapping("/w/{itemId}")
    public ResponseEntity<ItemDetailsDTO> getItemDetails(@PathVariable Integer itemId) {
        ItemDetailsDTO details = itemService.getItemDetails(itemId);

        if (details == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(details);
    }

}
