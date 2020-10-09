package project.service.utils;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.dto.AddressDTO;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Client;
import project.entity.Item;
import project.entity.Order;
import project.entity.enums.PaymentEnum;
import project.entity.enums.RoleEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestHelper {

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();
    private static ModelMapper mapper = new ModelMapper();

    private static List<Client> clientList = new ArrayList<>();
    private static List<OrderDTO> orders = new ArrayList<>();
    private static List<Order> entOrders = new ArrayList<>();
    private static List<Item> items = new ArrayList<>();

    public static CartDTO getCart(long id, ItemDTO item, int quantity, OrderDTO order) {
        CartDTO cart = new CartDTO();
        cart.setId(id);
        cart.setItem(item);
        cart.setQuantity(quantity);
        cart.setOrder(order);
        return cart;
    }

    public static OrderDTO getOrder(long id, String num, ClientDTO client, List<CartDTO> items) {
        OrderDTO order = new OrderDTO();
        order.setShipmentMethod(ShipmentEnum.DOOR_TO_DOOR);
        order.setPaymentStatus(true);
        order.setPaymentMethod(PaymentEnum.CARD);
        order.setStatus(StatusEnum.DELIVERED);
        order.setDate(Date.valueOf("2020-05-05"));

        order.setId(id);
        order.setOrderNo(num);
        order.setClient(client);
        order.setItems(items);

        BigDecimal subtotal = BigDecimal.ZERO;
        for (CartDTO cart : items) {
            ItemDTO item = cart.getItem();
            int quantity = cart.getQuantity();
            subtotal = subtotal.add(item.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        order.setSubtotal(subtotal);

        orders.add(order);
        entOrders.add(mapper.map(order, Order.class));

        return order;
    }

    public static ItemDTO getItem(long id, String name, String group) {
        ItemDTO item = new ItemDTO();
        item.setPathToIMG("path");
        item.setWeight(0.1);
        item.setVolume(0.1);
        item.setDescription("description");
        item.setStock(10);
        item.setPrice(BigDecimal.valueOf(10));

        item.setId(id);
        item.setItemName(name);
        item.setItemGroup(group);

        items.add(mapper.map(item, Item.class));
        return item;
    }

    public static AddressDTO getAddress(long id, int apart) {
        AddressDTO address = new AddressDTO();
        address.setCountry("Russia");
        address.setCity("Spb");
        address.setPostcode("123456");
        address.setStreet("Lenina");
        address.setBuilding(1);
        address.setId(id);
        address.setApart(apart);
        return address;
    }

    public static ClientDTO getClient(long id, String firstName, String lastName, String email, Set<AddressDTO> addresses) {
        ClientDTO client = new ClientDTO();
        client.setRole(RoleEnum.USER);
        client.setUserPass(encoder.encode("password"));
        client.setActive(true);
        client.setBirthDate(Date.valueOf("1990-11-15"));

        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setEmail(email);
        client.setAddressList(addresses);

        clientList.add(mapper.map(client, Client.class));
        return client;
    }

    public static List<Client> getClientList() {
        return clientList;
    }

    public static List<OrderDTO> getOrders() {
        return orders;
    }

    public static List<Item> getItems() {
        return items;
    }

    public static List<Order> getEntOrders() {
        return entOrders;
    }

    public static OrderDTO convertToDTO(OrderDTO order) {
        return order;
    }

    public static void flush() {
        clientList = new ArrayList<>();
        orders = new ArrayList<>();
        entOrders = new ArrayList<>();
        items = new ArrayList<>();
    }
}
