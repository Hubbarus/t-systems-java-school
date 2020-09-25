package project.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.OrderConverter;
import project.dao.AddressDao;
import project.dao.ClientDao;
import project.dao.ItemDao;
import project.dao.OrderDao;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Order;
import project.entity.enums.StatusEnum;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    @Autowired private final OrderDao orderDao;
    @Autowired private final OrderConverter orderConverter;
    @Autowired private final ClientDao clientDao;
    @Autowired private final AddressDao addressDao;
    @Autowired private final ItemDao itemDao;
    @Autowired private final ItemService itemService;

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
        return order
                .stream()
                .map(orderConverter::convertToDTO)
                .collect(Collectors.toList());
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

    public void createOrderAndSave(OrderDTO order) {
        order.setStatus(StatusEnum.NEW);

        for (CartDTO cartDTO : order.getItems()) {
            ItemDTO item = itemService.findById(cartDTO.getItem().getId());
            int quantity = cartDTO.getQuantity();

            item.setStock(item.getStock() - quantity);
            itemService.update(item);
        }

        save(order);
    }
}
