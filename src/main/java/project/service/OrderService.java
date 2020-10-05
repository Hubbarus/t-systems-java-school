package project.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.converter.OrderConverter;
import project.dao.OrderDao;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Order;
import project.entity.enums.StatusEnum;
import project.utils.OrderNumberGenerator;
import project.utils.StatByDateHolder;
import project.utils.TopTenComparator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void createOrderAndSave(OrderDTO order) {
        order.setStatus(StatusEnum.NEW);

        Date currentDate = new Date();
        order.setDate(new java.sql.Date(currentDate.getTime()));

        order.setOrderNo(generator.generate(order, findAll().size()));

        for (CartDTO cartDTO : order.getItems()) {
            ItemDTO item = itemService.findById(cartDTO.getItem().getId());
            int quantity = cartDTO.getQuantity();

            item.setStock(item.getStock() - quantity);
            itemService.update(item);
        }

        Order orderToSave = orderConverter.convertToEntity(order);
        orderDao.save(orderToSave);
    }

    public List<CartDTO> getTopTenItems() {
        List<OrderDTO> orders = findAll();
        Map<ItemDTO, Integer> allItemsWithQuantities = getMapOfAllSoldItems(orders);

        List<Map.Entry<ItemDTO, Integer>> listOfEntries = new ArrayList<>(allItemsWithQuantities.entrySet());

        listOfEntries.sort(new TopTenComparator<ItemDTO>().reversed());

        List<CartDTO> resultList = new ArrayList<>();
        for (Map.Entry<ItemDTO, Integer> entry : listOfEntries) {
            CartDTO cart = new CartDTO();
            cart.setItem(entry.getKey());
            cart.setQuantity(entry.getValue());
            resultList.add(cart);
        }

        return resultList.size() > 10 ? getFirstTenElements(resultList) : resultList;
    }

    private <T> List<T> getFirstTenElements(List<T> source) {
        List<T> tenElements = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tenElements.add(source.get(i));
        }
        return tenElements;
    }

    private Map<ItemDTO, Integer> getMapOfAllSoldItems(List<OrderDTO> orders) {
        Map<ItemDTO, Integer> resultMap = new HashMap<>();
        for (OrderDTO order : orders) {
            List<CartDTO> items = order.getItems();
            for (CartDTO cart : items) {
                ItemDTO item = cart.getItem();
                if (resultMap.containsKey(item)) {
                    Integer integer = resultMap.get(item);
                    resultMap.put(item, integer + cart.getQuantity());
                } else {
                    resultMap.put(item, cart.getQuantity());
                }
            }
        }

        return resultMap;
    }

    public List<Map.Entry<ClientDTO, Integer>> getTopTenClients() {
        List<OrderDTO> orders = findAll();
        Map<ClientDTO, Integer> allClientsWithNumOfOrders = getMapOfClientOrders(orders);

        List<Map.Entry<ClientDTO, Integer>> listOfEntries = new ArrayList<>(allClientsWithNumOfOrders.entrySet());

        listOfEntries.sort(new TopTenComparator<ClientDTO>().reversed());

        return listOfEntries.size() > 10 ? getFirstTenElements(listOfEntries) : listOfEntries;
    }

    private Map<ClientDTO, Integer> getMapOfClientOrders(List<OrderDTO> orders) {
        Map<ClientDTO, Integer> resultMap = new HashMap<>();
        for (OrderDTO order : orders) {
            ClientDTO client = order.getClient();
            if (resultMap.containsKey(client)) {
                int orderQuan = resultMap.get(client);
                resultMap.put(client, orderQuan + 1);
            } else {
                resultMap.put(client, 1);
            }
        }
        return resultMap;
    }

    public StatByDateHolder getSalesBetweenDates(StatByDateHolder holder) {
        LocalDate from = holder.getFrom().toLocalDate();
        LocalDate to = holder.getTo().toLocalDate();

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
}
