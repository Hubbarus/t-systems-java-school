package project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"carts"})
public class ItemDTO implements Serializable {
    private long id;
    private String itemName;
    private String itemGroup;
    private BigDecimal price;
    private String description;
    private double weight;
    private double volume;
    private int stock;
    private String pathToIMG;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CartDTO> carts = new HashSet<>();
}
