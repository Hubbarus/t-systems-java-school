package project.dto;

import lombok.Data;
import project.entity.Order;

@Data
public class CartDTO {
    private long id;
    private Order order;
    private ItemDTO item;
    private int quantity;
}
