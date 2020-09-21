package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.entity.Order;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private long id;
    private Order order;
    private ItemDTO item;
    private int quantity;
}
