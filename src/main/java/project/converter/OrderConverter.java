package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Cart;
import project.entity.Item;
import project.entity.Order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class OrderConverter {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ItemConverter itemConverter;

    public Order convertToEntity(OrderDTO order) {
        Order entity = mapper.map(order, Order.class);
        HashMap<ItemDTO, Integer> itemDTOS = order.getItems();
        Set<Cart> carts = new HashSet<>();
        for (Map.Entry<ItemDTO, Integer> mapEntry : itemDTOS.entrySet()) {
            Item item = itemConverter.convertToEntity(mapEntry.getKey());
            int quantity = mapEntry.getValue();

            Cart cart = new Cart();
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
        Set<Cart> carts = order.getCarts();
        HashMap<ItemDTO, Integer> items = new HashMap<>();

        for (Cart cart : carts) {
            ItemDTO item = itemConverter.convertToDTO(cart.getItem());
            int quantity = cart.getQuantity();
            items.put(item, quantity);
            subtotal = subtotal.add(BigDecimal.valueOf(item.getPrice() * quantity));
        }

        dto.setSubtotal(subtotal);
        dto.setItems(items);
        return dto;
    }
}
