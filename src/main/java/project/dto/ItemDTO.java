package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
