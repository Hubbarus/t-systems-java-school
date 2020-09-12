package project.dto;

import project.entity.Addresses;
import project.entity.Clients;
import project.entity.Products;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import java.util.HashMap;
import java.util.Objects;

public class OrderDTO {
    private PaymentEnum payMethod;
    private ShipmentEnum shipMethod;
    private boolean payStatus;
    private StatusEnum status;
    private Clients client;
    private Addresses address;
    private HashMap<Products, Integer> products;
    private double subtotal;

    public PaymentEnum getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PaymentEnum payMethod) {
        this.payMethod = payMethod;
    }

    public ShipmentEnum getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(ShipmentEnum shipMethod) {
        this.shipMethod = shipMethod;
    }

    public boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return isPayStatus() == orderDTO.isPayStatus() &&
                Double.compare(orderDTO.getSubtotal(), getSubtotal()) == 0 &&
                getPayMethod() == orderDTO.getPayMethod() &&
                getShipMethod() == orderDTO.getShipMethod() &&
                getStatus() == orderDTO.getStatus() &&
                Objects.equals(getClient(), orderDTO.getClient()) &&
                Objects.equals(getAddress(), orderDTO.getAddress()) &&
                Objects.equals(getProducts(), orderDTO.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPayMethod(), getShipMethod(), isPayStatus(), getStatus(), getClient(), getAddress(), getProducts(), getSubtotal());
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "payMethod=" + payMethod +
                ", shipMethod=" + shipMethod +
                ", payStatus=" + payStatus +
                ", status=" + status +
                ", client=" + client +
                ", address=" + address +
                ", products=" + products +
                ", subtotal=" + subtotal +
                '}';
    }
}
