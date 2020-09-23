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
import project.dto.OrderDTO;
import project.entity.Cart;
import project.entity.Item;
import project.entity.Order;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
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

    public void createOrderAndSave(Long addressId, Long clientId, List<CartDTO> items,
                                   PaymentEnum payMethod, ShipmentEnum shipMethod, boolean payStatus) {
        Order order = new Order();

        order.setClient(clientDao.findById(clientId));
        order.setAddress(addressDao.findById(addressId));
        order.setPaymentMethod(payMethod);
        order.setPaymentStatus(payStatus);
        order.setShipmentMethod(shipMethod);
        order.setStatus(StatusEnum.NEW);

        for (CartDTO cartDTO : items) {
            Item item = itemDao.findById(cartDTO.getItem().getId());
            int quantity = cartDTO.getQuantity();

            item.setStock(item.getStock() - quantity);

            Cart cart = new Cart();
            cart.setQuantity(quantity);
            item.addCart(cart);
            order.addCart(cart);

            itemDao.save(item);
        }

        orderDao.save(order);
    }
}
