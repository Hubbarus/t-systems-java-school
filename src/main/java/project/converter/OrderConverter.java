package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Cart;
import project.entity.Item;
import project.entity.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author paulponomarev
 * Converter class for {@link Order} to {@link OrderDTO}.
 */
@Component
public class OrderConverter {

    @Autowired private ModelMapper mapper;
    @Autowired private CartConverter cartConverter;

    public Order convertToEntity(OrderDTO order) {
        Order entity = mapper.map(order, Order.class);
        List<CartDTO> cartDTOS = order.getItems();
        List<Cart> carts = new ArrayList<>();

        for (CartDTO cartDTO : cartDTOS) {
            Cart cart = cartConverter.convertToEntity(cartDTO);
            cart.setOrder(entity);
            cart.setItem(mapper.map(cartDTO.getItem(), Item.class));
            carts.add(cart);
        }
        entity.setCarts(carts);

        return entity;
    }

    public OrderDTO convertToDTO(Order order) {
        OrderDTO dto = mapper.map(order, OrderDTO.class);
        BigDecimal subtotal = BigDecimal.ZERO;
        List<Cart> carts = order.getCarts();
        List<CartDTO> cartDTOS = new ArrayList<>();

        for (Cart cart : carts) {
            int quantity = cart.getQuantity();
            CartDTO cartDTO = cartConverter.convertToDTO(cart);
            cartDTO.setItem(mapper.map(cart.getItem(), ItemDTO.class));
            cartDTO.setOrder(dto);
            cartDTOS.add(cartDTO);

            subtotal = subtotal.add(cart.getItem().getPrice().multiply(BigDecimal.valueOf(quantity)));
        }

        dto.setSubtotal(subtotal);
        dto.setItems(cartDTOS);
        return dto;
    }
}
