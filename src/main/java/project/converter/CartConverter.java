package project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dto.CartDTO;
import project.entity.Cart;

@Service
public class CartConverter {
    @Autowired
    private ItemConverter itemConverter;

    public Cart convertToEntity(CartDTO dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setItem(itemConverter.convertToEntity(dto.getItem()));
        cart.setQuantity(dto.getQuantity());

        return cart;
    }


    public CartDTO convertToDTO(Cart entity) {
        CartDTO dto = new CartDTO();
        dto.setQuantity(entity.getQuantity());
        dto.setItem(itemConverter.convertToDTO(entity.getItem()));
        dto.setId(entity.getId());

        return dto;
    }
}
