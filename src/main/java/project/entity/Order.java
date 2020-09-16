package project.entity;

import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Order implements Serializable {
    private long id;
    private PaymentEnum paymentMethod;
    private ShipmentEnum shipmentMethod;
    private boolean paymentStatus;
    private StatusEnum status;
    private Client client;
    private Address address;
    private Collection<Item> items;

    public void addItem(Item item) {
        items.add(item);
        item.getOrders().add(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.getOrders().remove(this);
    }

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
    @Column(name = "payment_method", nullable = false, length = -1)
    @Enumerated(EnumType.STRING)
    public PaymentEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Basic
    @Column(name = "shipment_method", nullable = false, length = -1)
    @Enumerated(EnumType.STRING)
    public ShipmentEnum getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipmentEnum shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    @Basic
    @Column(name = "payment_status", nullable = false)
    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
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

        Order order = (Order) o;

        if (id != order.id) return false;
        if (paymentStatus != order.paymentStatus) return false;
        if (paymentMethod != null ? !paymentMethod.equals(order.paymentMethod) : order.paymentMethod != null)
            return false;
        if (shipmentMethod != null ? !shipmentMethod.equals(order.shipmentMethod) : order.shipmentMethod != null)
            return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (shipmentMethod != null ? shipmentMethod.hashCode() : 0);
        result = 31 * result + (paymentStatus ? 1 : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", paymentMethod=" + paymentMethod +
                ", shipmentMethod=" + shipmentMethod +
                ", paymentStatus=" + paymentStatus +
                ", status=" + status +
                ", client=" + client +
                ", address=" + address +
                '}';
    }

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Item.class)
    @JoinTable(name = "cart",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}
