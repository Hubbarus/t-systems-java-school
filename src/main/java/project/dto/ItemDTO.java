package project.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private long id;
    private String itemName;
    private String itemGroup;
    private double price;
    private String description;
    private double weight;
    private double volume;
    private int stock;
}
