package project.converter;

import org.springframework.stereotype.Component;
import project.dto.CartDTO;
import project.entity.Cart;

/**
 * @author paulponomarev
 * Converter class for {@link Cart} to {@link CartDTO}.
 */
@Component
public class CartConverter {

    public Cart convertToEntity(CartDTO dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setQuantity(dto.getQuantity());
        return cart;
    }

    public CartDTO convertToDTO(Cart entity) {
        CartDTO dto = new CartDTO();
        dto.setQuantity(entity.getQuantity());
        dto.setId(entity.getId());
        return dto;
    }
}
