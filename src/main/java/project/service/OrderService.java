package project.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.converter.OrderConverter;
import project.dao.OrderDao;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Client;
import project.entity.Order;
import project.entity.enums.StatusEnum;
import project.exception.OutOfStockException;
import project.utils.OrderNumberGenerator;
import project.utils.StatByDateHolder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for {@link OrderDTO} objects.
 */
@Service
@AllArgsConstructor
public class OrderService {

    @Autowired private final OrderDao orderDao;
    @Autowired private final OrderConverter orderConverter;
    @Autowired private final ItemService itemService;
    @Autowired private final OrderNumberGenerator generator;

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

    public List<OrderDTO> getOrders(int from, int quantity) {
        List<Order> orders = orderDao.getOrders(from, quantity);
        return orders
                .stream()
                .map(orderConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public void createOrderAndSave(OrderDTO order) throws OutOfStockException {
        order.setStatus(StatusEnum.NEW);

        Date currentDate = new Date();
        order.setDate(new java.sql.Date(currentDate.getTime()));

        order.setOrderNo(generator.generate(order, findAll().size()));

        for (CartDTO cartDTO : order.getItems()) {
            ItemDTO item = itemService.findById(cartDTO.getItem().getId());
            int quantity = cartDTO.getQuantity();
            int stock = item.getStock();

            if (quantity > stock) {
                throw new OutOfStockException(String.format("Somebody bought %s just now", item.getItemName()));
            }

            item.setStock(stock - quantity);
            itemService.update(item);
        }

        Order orderToSave = orderConverter.convertToEntity(order);
        orderDao.save(orderToSave);
    }

    public StatByDateHolder getSalesBetweenDates(StatByDateHolder holder) {
        LocalDate from = holder.getFrom();
        LocalDate to = holder.getTo();

        BigDecimal total = BigDecimal.ZERO;
        List<OrderDTO> orders = new ArrayList<>();
        List<OrderDTO> allOrders = findAll();
        for (OrderDTO order : allOrders) {
            LocalDate orderDate = order.getDate().toLocalDate();

            if (orderDate.isBefore(to)
                    && orderDate.isAfter(from)
                    || orderDate.equals(to)
                    || orderDate.equals(from)) {
                orders.add(order);
                total = total.add(order.getSubtotal());
            }
        }

        holder.setOrders(orders);
        holder.setProceeds(total);

        return holder;
    }

    public void updateOrderInformation(OrderDTO order) {
        OrderDTO updtOrder = findById(order.getId());
        updtOrder.setStatus(order.getStatus());
        updtOrder.setPaymentStatus(order.isPaymentStatus());

        Order orderToUpdate = orderConverter.convertToEntity(updtOrder);
        orderDao.update(orderToUpdate);
    }

    public List<OrderDTO> getAllClientOrders(Client user) {
        List<Order> orders = orderDao.getAllClientOrders(user);
        return orders.stream()
                .map(orderConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
