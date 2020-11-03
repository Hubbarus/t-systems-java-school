package project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author paulponomarev
 * Item DTO class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"carts"})
public class ItemDTO implements Serializable {
    private long id;
    @Size(min = 2, message = "Item Name length must be at least 2 characters")
    private String itemName;
    @Size(min = 2, message = "Item Category Name length must be at least 2 characters")
    private String itemGroup;
    @Digits(integer = 8, fraction = 2, message = "Price must be digit")
    @DecimalMin(value = "0.1", message = "Price must be at least 10 cents")
    private BigDecimal price;
    @Size(min = 5, message = "Description must be at least 5 characters")
    private String description;
    @Digits(integer = 8, fraction = 2, message = "Weight must be digit")
    @DecimalMin(value = "0.1", message = "Weight must be at least 100 gr")
    private double weight;
    @Digits(integer = 8, fraction = 2, message = "Volume must be digit")
    @DecimalMin(value = "0.1", message = "Volume must be at least 100 ccm")
    private double volume;
    @Digits(integer = 8, fraction = 2, message = "Quantity in stock must be digit")
    private int stock;
    private String pathToIMG;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CartDTO> carts = new HashSet<>();
}
