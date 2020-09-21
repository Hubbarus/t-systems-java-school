package project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Order implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentEnum paymentMethod;
    @Column(name = "shipment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipmentEnum shipmentMethod;
    @Column(name = "payment_status", nullable = false)
    private boolean paymentStatus;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    public void addCart(Cart cart) {
        carts.add(cart);
        cart.setOrder(this);
    }

    public void removeCart(Cart cart) {
        cart.setOrder(null);
        carts.remove(cart);
    }
}
