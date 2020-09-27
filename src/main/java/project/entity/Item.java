package project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
public class Item  implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "item_name", nullable = false)
    private String itemName;
    @Column(name = "item_group", nullable = false)
    private String itemGroup;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "weight", nullable = false)
    private double weight;
    @Column(name = "volume", nullable = false)
    private double volume;
    @Column(name = "stock", nullable = false)
    private int stock;
    @Column(name = "image")
    private String pathToIMG;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Cart> carts = new HashSet<>();

    public void addCart(Cart cart) {
        carts.add(cart);
        cart.setItem(this);
    }

    public void removeCart(Cart cart) {
        cart.setItem(null);
        carts.remove(cart);
    }
}
