package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    private int id;
    private String payMethod;
    private String shipMethod;
    private Boolean payStatus;
    private String status;
    private Client clientByClientId;
    private Address addressByClientAddressId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pay_method", nullable = true, length = -1)
    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    @Basic
    @Column(name = "ship_method", nullable = true, length = -1)
    public String getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(String shipMethod) {
        this.shipMethod = shipMethod;
    }

    @Basic
    @Column(name = "pay_status", nullable = true)
    public Boolean getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Boolean payStatus) {
        this.payStatus = payStatus;
    }

    @Basic
    @Column(name = "status", nullable = true, length = -1)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (payMethod != null ? !payMethod.equals(order.payMethod) : order.payMethod != null) return false;
        if (shipMethod != null ? !shipMethod.equals(order.shipMethod) : order.shipMethod != null) return false;
        if (payStatus != null ? !payStatus.equals(order.payStatus) : order.payStatus != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (payMethod != null ? payMethod.hashCode() : 0);
        result = 31 * result + (shipMethod != null ? shipMethod.hashCode() : 0);
        result = 31 * result + (payStatus != null ? payStatus.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public Client getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(Client clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    @ManyToOne
    @JoinColumn(name = "client_address_id", referencedColumnName = "id", nullable = false)
    public Address getAddressByClientAddressId() {
        return addressByClientAddressId;
    }

    public void setAddressByClientAddressId(Address addressByClientAddressId) {
        this.addressByClientAddressId = addressByClientAddressId;
    }
}
