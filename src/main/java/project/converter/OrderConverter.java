package project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.OrderDTO;
import project.entity.Order;

@Service
@Transactional
public class OrderConverter {

    @Autowired
    private ModelMapper mapper;

    public Order convertToEntity(OrderDTO order) {
        Order entity = mapper.map(order, Order.class);
        return entity;
    }

    public OrderDTO convertToDTO(Order order) {
        OrderDTO dto = mapper.map(order, OrderDTO.class);
        return dto;
    }
}
