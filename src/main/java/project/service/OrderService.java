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

    public void save(OrderDTO orderDTO) {
        Order order = orderConverter.convertToEntity(orderDTO);
        orderDao.openCurrentSessionwithTransaction();
        orderDao.save(order);
        orderDao.closeCurrentSessionwithTransaction();
    }

    public void update(OrderDTO orderDTO) {
        Order order = orderConverter.convertToEntity(orderDTO);
        orderDao.openCurrentSessionwithTransaction();
        orderDao.update(order);
        orderDao.closeCurrentSessionwithTransaction();
    }

    @Transactional
    public OrderDTO findById(Long id) {
        orderDao.openCurrentSession();
        Order order = orderDao.findById(id);
        orderDao.closeCurrentSession();
        return orderConverter.convertToDTO(order);
    }

    @Transactional
    public List<OrderDTO> findAll() {
        orderDao.openCurrentSession();
        List<Order> order = orderDao.findAll();
        orderDao.closeCurrentSession();
        List<OrderDTO> orderDTOS = order
                .stream()
                .map(orderConverter::convertToDTO)
                .collect(Collectors.toList());
        return orderDTOS;
    }

    public void delete(OrderDTO orderDTO) {
        Order order = orderConverter.convertToEntity(orderDTO);
        orderDao.openCurrentSessionwithTransaction();
        orderDao.delete(order);
        orderDao.closeCurrentSessionwithTransaction();
    }

    public void deleteAll() {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.deleteAll();
        orderDao.closeCurrentSessionwithTransaction();
    }
    // TODO add item and so on
}
