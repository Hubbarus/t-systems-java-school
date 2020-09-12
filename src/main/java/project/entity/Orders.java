package project.entity;

import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import java.io.Serializable;
import java.util.HashMap;

@Entity
public class Orders implements Serializable {
    private long id;
    private PaymentEnum payMethod;
    private ShipmentEnum shipMethod;
    private boolean payStatus;
    private StatusEnum status;
    private Clients client;
    private Addresses address;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "cart",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private HashMap<Products, Integer> products;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pay_method", nullable = false, length = -1)
    @Enumerated(EnumType.STRING)
    public PaymentEnum getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PaymentEnum payMethod) {
        this.payMethod = payMethod;
    }

    @Basic
    @Column(name = "ship_method", nullable = false, length = -1)
    @Enumerated(EnumType.STRING)
    public ShipmentEnum getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(ShipmentEnum shipMethod) {
        this.shipMethod = shipMethod;
    }

    @Basic
    @Column(name = "pay_status", nullable = false)
    public boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }

    @Basic
    @Column(name = "status", nullable = false, length = -1)
    @Enumerated(EnumType.STRING)
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (id != orders.id) return false;
        if (payStatus != orders.payStatus) return false;
        if (payMethod != null ? !payMethod.equals(orders.payMethod) : orders.payMethod != null) return false;
        if (shipMethod != null ? !shipMethod.equals(orders.shipMethod) : orders.shipMethod != null) return false;
        if (status != null ? !status.equals(orders.status) : orders.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (payMethod != null ? payMethod.hashCode() : 0);
        result = 31 * result + (shipMethod != null ? shipMethod.hashCode() : 0);
        result = 31 * result + (payStatus ? 1 : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order {" +
                "id=" + id +
                ", payMethod='" + payMethod + '\'' +
                ", shipMethod='" + shipMethod + '\'' +
                ", payStatus=" + payStatus +
                ", status=" + status +
                ", client=" + client +
                ", address=" + address +
                ", products=" + products +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    public HashMap<Products, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Products, Integer> products) {
        this.products = products;
    }
}
