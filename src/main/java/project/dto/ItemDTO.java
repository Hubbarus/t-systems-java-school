package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
    private String pathToIMG;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CartDTO> carts = new HashSet<>();

    public void addCart(CartDTO cart) {
        carts.add(cart);
        cart.setItem(this);
    }

    public void removeCart(CartDTO cart) {
        cart.setItem(null);
        carts.remove(cart);
    }
}
