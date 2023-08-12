package com.svg.D2Back.projection;

import lombok.Data;

import java.util.List;

@Data
public class ItemDetailsDTO {
    private ItemJsonDTO itemData;
    private List<StatDetailDTO> stats;
}
