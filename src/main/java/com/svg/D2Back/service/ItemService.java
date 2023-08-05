package com.svg.D2Back.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svg.D2Back.entity.DisplayProperties;
import com.svg.D2Back.entity.Item;
import com.svg.D2Back.projection.ItemProjection;
import com.svg.D2Back.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DataSource dataSource;

    public List<ItemProjection> findAll() {
        return itemRepository.findItemsWithSelectedColumns();
    }

    public List<Item> findItemsByName(String name) throws SQLException, JsonProcessingException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM DestinyInventoryItemDefinition WHERE json_extract(json, '$.displayProperties.name')=?";
        ObjectMapper mapper = new ObjectMapper();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item();
                    item.setHash(rs.getInt("id"));
                    String jsonContent = rs.getString("json");
                    JsonNode jsonNode = mapper.readTree(jsonContent);
                    JsonNode displayPropertiesNode = jsonNode.path("displayProperties");
                    DisplayProperties displayProperties = mapper.treeToValue(displayPropertiesNode, DisplayProperties.class);
                    item.setDisplayProperties(displayProperties);
                    items.add(item);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return items;
        }
    }

}
