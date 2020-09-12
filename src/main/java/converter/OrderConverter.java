package converter;

import dto.OrderDTO;
import entity.Orders;
import entity.Products;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderConverter {

    @Autowired
    private ModelMapper mapper;

    public Orders convertToEntity(OrderDTO order) {
        Orders entity = mapper.map(order, Orders.class);
        return entity;
    }

    public OrderDTO convertToDTO(Orders order) {
        OrderDTO dto = mapper.map(order, OrderDTO.class);
        Map<Products, Integer> list = order.getProducts();
        double subtotal = 0;
        for (Map.Entry<Products, Integer> entry : list.entrySet()) {
            subtotal += (entry.getKey().getPrice() * entry.getValue());
        }

        dto.setSubtotal(subtotal);
        return dto;
    }
}
