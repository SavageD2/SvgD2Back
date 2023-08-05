package com.svg.D2Back.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.svg.D2Back.Errors.RessourceNotFoundException;
import com.svg.D2Back.entity.Item;
import com.svg.D2Back.projection.ItemProjection;
import com.svg.D2Back.repository.ItemRepository;
import com.svg.D2Back.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public ResponseEntity<List<ItemProjection>> getItems(){
        List<ItemProjection> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemProjection> getItem(@PathVariable Integer itemId){
        ItemProjection item = itemRepository.findByHash(itemId)
                .orElseThrow(() -> new RessourceNotFoundException("Item", "hash", itemId));
        return new ResponseEntity<>(item, HttpStatus.OK);

    }

    @GetMapping("/filter")
    public ResponseEntity<List<Item>> searchItems(@RequestParam String name) throws SQLException, JsonProcessingException {
        List<Item> items = itemService.findItemsByName(name);
        return ResponseEntity.ok(items);
    }
}
