package project.dto;

import lombok.Data;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class OrderDTO {
    private long id;
    private PaymentEnum paymentMethod;
    private ShipmentEnum shipmentMethod;
    private boolean paymentStatus;
    private StatusEnum status;
    private ClientDTO client;
    private AddressDTO address;
    private Set<CartDTO> items;
    private BigDecimal subtotal;
}
