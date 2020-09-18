package project.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDTO {
    private long id;
    private String itemName;
    private String itemGroup;
    private BigDecimal price;
    private String description;
    private double weight;
    private double volume;
    private int stock;
}
