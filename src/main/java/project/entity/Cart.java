package project.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

    @OneToMany
    @JoinTable(name = "order", joinColumns = @JoinColumn(name = "id"))
    private Order order;


    private Item item;

    private int quantity;
}
