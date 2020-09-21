package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Cart;
import project.entity.Item;
import project.entity.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderConverter {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ItemConverter itemConverter;

    public Order convertToEntity(OrderDTO order) {
        Order entity = mapper.map(order, Order.class);
        List<CartDTO> cartDTOS = order.getItems();
        List<Cart> carts = new ArrayList<>();

        for (CartDTO cartDTO : cartDTOS) {
            Item item = itemConverter.convertToEntity(cartDTO.getItem());
            int quantity = cartDTO.getQuantity();

            Cart cart = new Cart();
            cart.setOrder(cartDTO.getOrder());
            cart.setId(cartDTO.getId());
            cart.setItem(item);
            cart.setQuantity(quantity);
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
            ItemDTO item = itemConverter.convertToDTO(cart.getItem());
            int quantity = cart.getQuantity();
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(cart.getId());
            cartDTO.setItem(item);
            cartDTO.setOrder(order);
            cartDTO.setQuantity(quantity);

            cartDTOS.add(cartDTO);
            subtotal = subtotal.add(item.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }

        dto.setSubtotal(subtotal);
        dto.setItems(cartDTOS);
        return dto;
    }
}
