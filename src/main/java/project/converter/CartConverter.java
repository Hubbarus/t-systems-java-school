package project.converter;

import org.springframework.stereotype.Service;
import project.dto.CartDTO;
import project.entity.Cart;

@Service
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
