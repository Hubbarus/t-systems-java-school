package project.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
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
    private double price;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "weight", nullable = false)
    private double weight;
    @Column(name = "volume", nullable = false)
    private double volume;
    @Column(name = "stock", nullable = false)
    private int stock;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Cart> carts;
}
