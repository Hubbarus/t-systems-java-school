package project.service.utils;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Address;
import project.entity.Cart;
import project.entity.Client;
import project.entity.Item;
import project.entity.Order;
import project.entity.enums.PaymentEnum;
import project.entity.enums.RoleEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestHelper {

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();
    private static ModelMapper mapper = new ModelMapper();

    public static Order order1 = new Order();
    public static Order order2 = new Order();

    public static Address address1;
    public static Address address2;
    public static Address address3;
    public static Address address4;

    public static Client client1;
    public static Client client2;

    public static Item item1;
    public static Item item2;
    public static Item item3;
    public static Item item4;

    public static Cart cart1;
    public static Cart cart2;
    public static Cart cart3;
    public static Cart cart4;

    public static void initAddresses() {
        address1 = new Address();
        address1.setCountry("Russia");
        address1.setCity("Spb");
        address1.setBuilding(1);
        address1.setApart(1);
        address1.setPostcode("123456");
        address1.setStreet("Test street 1");

        address2 = new Address();
        address2.setCity("Russia");
        address2.setCity("Spb");
        address2.setBuilding(1);
        address2.setApart(1);
        address2.setPostcode("234567");
        address2.setStreet("Test street 2");

        address3 = new Address();
        address3.setCity("Russia");
        address3.setCity("Spb");
        address3.setBuilding(1);
        address3.setApart(1);
        address3.setPostcode("234567");
        address3.setStreet("Test street 3");

        address4 = new Address();
        address4.setCity("Russia");
        address4.setCity("Spb");
        address4.setBuilding(1);
        address4.setApart(1);
        address4.setPostcode("234567");
        address4.setStreet("Test street 4");
    }

    public static void initItems() {
        item1 = new Item();
        item1.setStock(20);
        item1.setPrice(BigDecimal.valueOf(1));
        item1.setPathToIMG("path");
        item1.setVolume(1.1);
        item1.setWeight(1.1);

        item2 = new Item();
        item2.setStock(20);
        item2.setPrice(BigDecimal.valueOf(2));
        item2.setPathToIMG("path2");
        item2.setVolume(2.2);
        item2.setWeight(2.2);

        item3 = new Item();
        item3.setStock(20);
        item3.setPrice(BigDecimal.valueOf(3));
        item3.setPathToIMG("path3");
        item3.setVolume(3.3);
        item3.setWeight(3.3);

        item4 = new Item();
        item4.setStock(20);
        item4.setPrice(BigDecimal.valueOf(4));
        item4.setPathToIMG("path4");
        item4.setVolume(4.4);
        item4.setWeight(4.4);
    }

    public static void initClients() {
        client1 = new Client();
        client1.setBirthDate(new Date(new java.util.Date().getTime()));
        client1.setActive(true);
        client1.setUserPass(encoder.encode("password"));
        client1.setRole(RoleEnum.USER);

        client2 = new Client();
        client2.setBirthDate(new Date(new java.util.Date().getTime()));
        client2.setActive(true);
        client2.setUserPass(encoder.encode("password"));
        client2.setRole(RoleEnum.USER);
    }

    public static void initOrders() {
        order1.setPaymentStatus(true);
        order1.setDate(Date.valueOf(LocalDate.of(2020, 1, 2)));
        order1.setStatus(StatusEnum.NEW);
        order1.setPaymentMethod(PaymentEnum.CARD);
        order1.setShipmentMethod(ShipmentEnum.DOOR_TO_DOOR);

        order2.setPaymentStatus(true);
        order1.setDate(Date.valueOf(LocalDate.of(2020, 1, 2)));
        order2.setStatus(StatusEnum.NEW);
        order2.setPaymentMethod(PaymentEnum.CARD);
        order2.setShipmentMethod(ShipmentEnum.DOOR_TO_DOOR);
    }

    public static void initCarts() {
        cart1 = new Cart();
        cart1.setItem(item1);
        cart1.setOrder(order1);
        cart1.setQuantity(1);
        cart1.setId(1);

        cart2 = new Cart();
        cart2.setItem(item2);
        cart2.setOrder(order1);
        cart2.setQuantity(2);
        cart2.setId(2);

        cart3 = new Cart();
        cart3.setItem(item3);
        cart3.setOrder(order2);
        cart3.setQuantity(3);
        cart3.setId(3);

        cart4 = new Cart();
        cart4.setItem(getItem4());
        cart4.setOrder(order2);
        cart4.setQuantity(4);
        cart4.setId(4);
    }

    public static List<Client> getAllClients() {
        setAddress1ID(1);
        setAddress2ID(2);
        setAddress3ID(3);
        setAddress4ID(4);

        Set<Address> addresses1 = Set.of(address1, address2);
        Set<Address> addresses2 = Set.of(address3, address4);

        Client client1 = getClient1();
        client1.setAddressList(addresses1);

        Client client2 = getClient2();
        client2.setAddressList(addresses2);

        return List.of(client1, client2);
    }

    public static List<Item> getAllItems() {
        return List.of(getItem1(), getItem2(), getItem3(), getItem4());
    }

    public static List<Order> getAllOrders() {
        List<Order> orders = List.of(order1, order2);
        return orders;
    }

    public static OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setStatus(order.getStatus());
        dto.setDate(order.getDate());
        dto.setOrderNo(order.getOrderNo());
        dto.setId(order.getId());
        dto.setClient(mapper.map(order.getClient(), ClientDTO.class));
        dto.setShipmentMethod(order.getShipmentMethod());

        BigDecimal subtotal = BigDecimal.ZERO;
        List<Cart> carts = order.getCarts();
        List<CartDTO> cartDTOS = new ArrayList<>();

        for (Cart cart : carts) {
            int quantity = cart.getQuantity();
            CartDTO cartDTO = new CartDTO();
            cartDTO.setQuantity(cart.getQuantity());
            cartDTO.setId(cart.getId());
            cartDTO.setItem(mapper.map(cart.getItem(), ItemDTO.class));
            cartDTO.setOrder(dto);
            cartDTOS.add(cartDTO);

            subtotal = subtotal.add(cart.getItem().getPrice().multiply(BigDecimal.valueOf(quantity)));
        }

        dto.setSubtotal(subtotal);
        dto.setItems(cartDTOS);
        return dto;
    }

    public static void setAddress1ID(long id) {
        address1.setId(id);
    }

    public static void setAddress2ID(long id) {
        address2.setId(id);
    }

    public static void setAddress3ID(long id) {
        address3.setId(id);
    }

    public static void setAddress4ID(long id) {
        address4.setId(id);
    }

    public static Client getClient1() {
        client1.setFirstName("Test First Name 1");
        client1.setLastName("Test Last Name 1");
        client1.setEmail("test1@email.com");
        client1.setId(1);
        return client1;
    }

    public static Client getClient2() {
        client2.setFirstName("Test First Name 2");
        client2.setLastName("Test Last Name 2");
        client2.setEmail("test2@email.com");
        client2.setId(2);
        return client2;
    }

    public static Order getOrder1() {
        order1.setId(1);
        order1.setOrderNo("no1");
        order1.setClient(client1);
        List<Cart> carts = List.of(cart1, cart2);
        order1.setCarts(carts);
        return order1;
    }

    public static Order getOrder2() {
        order2.setId(2);
        order2.setOrderNo("no2");
        order2.setClient(client2);
        List<Cart> carts = List.of(cart3, cart4);
        order2.setCarts(carts);
        return order2;
    }

    public static Item getItem1() {
        item1.setId(1);
        item1.setItemGroup("Group1");
        item1.setItemName("ItemName1");
        item1.setDescription("Description1");
        return item1;
    }

    public static Item getItem2() {
        item2.setId(2);
        item2.setItemGroup("Group2");
        item2.setItemName("ItemName2");
        item2.setDescription("Description2");
        return item2;
    }

    public static Item getItem3() {
        item3.setId(3);
        item3.setItemGroup("Group3");
        item3.setItemName("ItemName3");
        item3.setDescription("Description3");
        return item3;
    }

    public static Item getItem4() {
        item4.setId(4);
        item4.setItemGroup("Group4");
        item4.setItemName("ItemName4");
        item4.setDescription("Description4");

        return item4;
    }
}
