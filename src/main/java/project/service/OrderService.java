package project.service;

import io.seruco.encoding.base62.Base62;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.OrderConverter;
import project.dao.OrderDao;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Order;
import project.entity.enums.StatusEnum;
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

    public void createOrderAndSave(OrderDTO order) {
        order.setStatus(StatusEnum.NEW);

        Date currentDate = new Date();
        order.setDate(new java.sql.Date(currentDate.getTime()));

        order.setOrderNo(generateOrderNo(order));

        for (CartDTO cartDTO : order.getItems()) {
            ItemDTO item = itemService.findById(cartDTO.getItem().getId());
            int quantity = cartDTO.getQuantity();

            item.setStock(item.getStock() - quantity);
            itemService.update(item);
        }

        save(order);
    }

    private String generateOrderNo(OrderDTO order) {
        StringBuilder num = new StringBuilder();

        switch (order.getPaymentMethod()) {
            case CARD: {
                num.append("CD");
                break;
            }
            case CASH: {
                num.append("CS");
                break;
            }
            case REMITTANCE: {
                num.append("R");
                break;
            }
        }

        Base62 base62 = Base62.createInstance();
        int val = findAll().size();
        byte[] arr = String.valueOf(val).getBytes();
        String str = new String(base62.encode(arr));
        num.append(str);

        switch (order.getShipmentMethod()) {
            case SELF_PICKUP: {
                num.append("SP");
                break;
            }
            case DOOR_TO_DOOR: {
                num.append("DTD");
                break;
            }
        }

        num.append(order.getDate().toLocalDate().getDayOfMonth());

        return num.toString();
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
        //Date from = new Date(holder.getFrom().getTime());
        //Date to = new Date(.getTime());

        LocalDate from = holder.getFrom().toLocalDate();
        LocalDate to = holder.getTo().toLocalDate();
        BigDecimal total = BigDecimal.ZERO;
        List<OrderDTO> orders = new ArrayList<>();
        List<OrderDTO> allOrders = findAll();
        for (OrderDTO order : allOrders) {
            //Date orderDate = new Date(order.getDate().getTime());
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
}
