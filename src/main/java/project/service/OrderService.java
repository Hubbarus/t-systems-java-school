package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.OrderConverter;
import project.dao.AddressDao;
import project.dao.ClientDao;
import project.dao.ItemDao;
import project.dao.OrderDao;
import project.dto.OrderDTO;
import project.entity.Cart;
import project.entity.Item;
import project.entity.Order;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderDao orderDao;
    private final OrderConverter orderConverter;
    private final ClientDao clientDao;
    private final AddressDao addressDao;
    private final ItemDao itemDao;


    @Autowired
    public OrderService(OrderDao orderDao,
                        OrderConverter orderConverter,
                        ClientDao clientDao, AddressDao addressDao, ItemDao itemDao) {
        this.orderDao = orderDao;
        this.orderConverter = orderConverter;
        this.clientDao = clientDao;
        this.addressDao = addressDao;
        this.itemDao = itemDao;
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

    public void createOrderAndSave(Long addressId, Long clientId, HashMap<Long, Integer> items,
                                   PaymentEnum payMethod, ShipmentEnum shipMethod, boolean payStatus) {
        Order order = new Order();

        order.setClient(clientDao.findById(clientId));
        order.setAddress(addressDao.findById(addressId));
        order.setPaymentMethod(payMethod);
        order.setPaymentStatus(payStatus);
        order.setShipmentMethod(shipMethod);
        order.setStatus(StatusEnum.NEW);

        Set<Cart> carts = new HashSet<>();
        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            Item item = itemDao.findById(entry.getKey());
            int quantity = entry.getValue();

            Cart cart = new Cart();
            cart.setItem(item);
            cart.setQuantity(quantity);
            //cart.setOrder(order);
            carts.add(cart);
        }

        order.setCarts(carts);
        orderDao.save(order);
    }
}
