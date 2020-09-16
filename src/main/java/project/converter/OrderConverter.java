package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Item;
import project.entity.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        Collection<Item> items = new ArrayList<>();
        for (Map.Entry<ItemDTO, Integer> entry : itemDTOS.entrySet()) {
            Item item = itemConverter.convertToEntity(entry.getKey());
            for (int i = 0; i < entry.getValue(); i++) {
                items.add(item);
            }
        }
        entity.setItems(items);
        return entity;
    }

    public OrderDTO convertToDTO(Order order) {
        OrderDTO dto = mapper.map(order, OrderDTO.class);
        Collection<Item> items = order.getItems();
        Collection<ItemDTO> itemDTOS = items
                .stream()
                .map(it -> itemConverter.convertToDTO(it))
                .collect(Collectors.toList());
        HashMap<ItemDTO, Integer> itemMap = new HashMap<>();
        for (ItemDTO item : itemDTOS) {
            if (itemMap.get(item) != null) {
                itemMap.put(item, itemMap.get(item) + 1);
            } else {
                itemMap.put(item, 1);
            }
        }
        dto.setItems(itemMap);
        return dto;
    }
}
