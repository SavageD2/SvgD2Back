package com.svg.D2Back.service;

import com.svg.D2Back.entity.Item;
import com.svg.D2Back.projection.ItemProjection;
import com.svg.D2Back.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<ItemProjection> findAll() {
        return itemRepository.findItemsWithSelectedColumns();
    }

    public List<Item> findItemsByName(String name) {
        String sql = "SELECT * FROM DestinyInventoryItemDefinition WHERE json_extract(json, '$.name') = :name";
        Query query = entityManager.createNativeQuery(sql, Item.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }
}
