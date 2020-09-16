package project.dto;

import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import java.util.HashMap;
import java.util.Objects;

public class OrderDTO {
    private long id;
    private PaymentEnum paymentMethod;
    private ShipmentEnum shipmentMethod;
    private boolean paymentStatus;
    private StatusEnum status;
    private ClientDTO client;
    private AddressDTO address;
    private HashMap<ItemDTO, Integer> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PaymentEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShipmentEnum getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipmentEnum shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return id == orderDTO.id &&
                paymentStatus == orderDTO.paymentStatus &&
                paymentMethod == orderDTO.paymentMethod &&
                shipmentMethod == orderDTO.shipmentMethod &&
                status == orderDTO.status &&
                Objects.equals(client, orderDTO.client) &&
                Objects.equals(address, orderDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, shipmentMethod, paymentStatus, status, client, address);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", paymentMethod=" + paymentMethod +
                ", shipmentMethod=" + shipmentMethod +
                ", paymentStatus=" + paymentStatus +
                ", status=" + status +
                ", client=" + client +
                ", address=" + address +
                ", items" + items +
                '}';
    }

    public HashMap<ItemDTO, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<ItemDTO, Integer> items) {
        this.items = items;
    }
}
