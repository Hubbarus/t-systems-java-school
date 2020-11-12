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

    /**
     * Finds {@link Order} entity in database and converts it to DTO
     * @param id of entity in database
     * @return {@link OrderDTO} object
     */
    public OrderDTO findById(Long id) {
        Order order = orderDao.findById(id);
        return orderConverter.convertToDTO(order);
    }

    /**
     * Finds all orders in database
     * @return list of {@link OrderDTO} objects
     */
    public List<OrderDTO> findAll() {
        List<Order> order = orderDao.findAll();
        return order
                .stream()
                .map(orderConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds orders for paging util
     * @param from number of page
     * @param quantity in one page
     * @return list of {@link OrderDTO} objects
     */
    public List<OrderDTO> getOrders(int from, int quantity) {
        List<Order> orders = orderDao.getOrders(from, quantity);
        return orders
                .stream()
                .map(orderConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Creates order and saves in database. Updates item quantity
     * @param order to save
     */
    public void createOrderAndSave(OrderDTO order) {
        order.setStatus(StatusEnum.NEW);

        Date currentDate = new Date();
        order.setDate(new java.sql.Date(currentDate.getTime()));

        order.setOrderNo(generator.generate(order, findAll().size()));

        for (CartDTO cartDTO : order.getItems()) {
            ItemDTO item = itemService.findById(cartDTO.getItem().getId());
            int quantity = cartDTO.getQuantity();
            int stock = item.getStock();

            item.setStock(stock - quantity);
            itemService.update(item);
        }

        Order orderToSave = orderConverter.convertToEntity(order);
        orderDao.save(orderToSave);
    }

    /**
     * Gets all sales of store between two dates
     * @param holder with date field
     * @return modified {@link StatByDateHolder} object
     */
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

    /**
     * Updates order information in database
     * @param order to be updated
     */
    public void updateOrderInformation(OrderDTO order) {
        OrderDTO updtOrder = findById(order.getId());
        updtOrder.setStatus(order.getStatus());
        updtOrder.setPaymentStatus(order.isPaymentStatus());

        Order orderToUpdate = orderConverter.convertToEntity(updtOrder);
        orderDao.update(orderToUpdate);
    }

    /**
     * Finds all client orders in database
     * @param user which orders to be found
     * @return list of {@link OrderDTO} objects
     */
    public List<OrderDTO> getAllClientOrders(Client user) {
        List<Order> orders = orderDao.getAllClientOrders(user);
        return orders.stream()
                .map(orderConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
