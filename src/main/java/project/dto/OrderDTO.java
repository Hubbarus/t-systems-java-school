package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private long id;
    private PaymentEnum paymentMethod;
    private ShipmentEnum shipmentMethod;
    private boolean paymentStatus;
    private StatusEnum status;
    private ClientDTO client;
    private AddressDTO address;
    private List<CartDTO> items;
    private BigDecimal subtotal;
}
