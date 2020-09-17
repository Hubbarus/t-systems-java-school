package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.OrderConverter;
import project.dao.OrderDao;
import project.dto.OrderDTO;
import project.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderDao orderDao;
    private final OrderConverter orderConverter;


    @Autowired
    public OrderService(OrderDao orderDao,
                        OrderConverter orderConverter) {
        this.orderDao = orderDao;
        this.orderConverter = orderConverter;
    }

    @Transactional
    public void save(OrderDTO orderDTO) {
        Order order = orderConverter.convertToEntity(orderDTO);
        orderDao.save(order);
    }

    @Transactional
    public void update(OrderDTO orderDTO) {
        Order order = orderConverter.convertToEntity(orderDTO);
        orderDao.update(order);
    }

    public OrderDTO findById(Long id) {
        Order order = orderDao.findById(id);
        return orderConverter.convertToDTO(order);
    }

    public List<OrderDTO> findAll() {
        List<Order> order = orderDao.findAll();
        List<OrderDTO> orderDTOS = order
                .stream()
                .map(orderConverter::convertToDTO)
                .collect(Collectors.toList());
        return orderDTOS;
    }

    @Transactional
    public void delete(OrderDTO orderDTO) {
        Order order = orderConverter.convertToEntity(orderDTO);
        orderDao.delete(order);
    }

    @Transactional
    public void deleteAll() {
        orderDao.deleteAll();
    }
}
