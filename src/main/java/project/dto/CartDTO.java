package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author paulponomarev
 * Cart DTO class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO implements Serializable {
    private long id;
    private OrderDTO order;
    private ItemDTO item;
    private int quantity;
}
