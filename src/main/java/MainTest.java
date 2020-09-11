
import entity.Addresses;
import entity.Clients;
import entity.Orders;
import entity.Products;
import entity.StatusEnum;
import service.AddressService;
import service.ClientService;
import service.OrderService;
import service.ProductService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class MainTest {
    static ProductService productService = new ProductService();
    static ClientService clientService = new ClientService();
    static AddressService addressService = new AddressService();
    static OrderService orderService = new OrderService();

    public static void main(String[] args) {
        createClients();
    }

    public static void createOrders() {
        Addresses address = addressService.findById(1);
        Clients client = clientService.findById(1);

        Map<Products, Integer> products = new HashMap<>();
        Products p1 = productService.findById(1);
        Products p2 = productService.findById(2);
        products.put(p1, 2);
        products.put(p2, 1);

        Orders order = new Orders();
        order.setAddress(address);
        order.setClient(client);
        order.setPayMethod("cash");
        order.setPayStatus(true);
        order.setShipMethod("delivery");
        order.setStatus(StatusEnum.NEW);
        //order.setProducts(products);

        orderService.save(order);
    }

    public static void createClients() {
        Clients client1 = new Clients();
        client1.setFirstName("Petr");
        client1.setLastName("Ivanov");
        client1.setBirthDate(Date.valueOf("1989-05-23"));
        client1.setEmail("example3@gmail.com");
        client1.setUserPass("pass3");
        client1.setUsername("user3");

        Clients client2 = new Clients();
        client2.setFirstName("Foma");
        client2.setLastName("Krenev");
        client2.setBirthDate(Date.valueOf("1976-03-03"));
        client2.setEmail("example4@gmail.com");
        client2.setUserPass("pass4");
        client2.setUsername("user4");

        clientService.save(client1);
        clientService.save(client2);
    }

    public static void createAddresses() {
        Addresses address1 = new Addresses();
        address1.setCountry("Russia");
        address1.setCity("Spb");
        address1.setPostcode(198531);
        address1.setStreet("Nevskiy prospect");
        address1.setBuilding(23);
        address1.setFlat(3);

        Addresses address2 = new Addresses();
        address2.setCountry("Ukraine");
        address2.setCity("Lviv");
        address2.setPostcode(19431);
        address2.setStreet("Lenina prospect");
        address2.setBuilding(64);
        address2.setFlat(654);

        Addresses address3 = new Addresses();
        address3.setCountry("Russia");
        address3.setCity("Moscow");
        address3.setPostcode(535311);
        address3.setStreet("Moscovskiy prospect");
        address3.setBuilding(53);
        address3.setFlat(555);

        Addresses address4 = new Addresses();
        address4.setCountry("Russia");
        address4.setCity("Spb");
        address4.setPostcode(197349);
        address4.setStreet("Griboedova naberezhnaya");
        address4.setBuilding(66);
        address4.setFlat(12);

        addressService.save(address1);
        addressService.save(address2);
        addressService.save(address3);
        addressService.save(address4);

    }

    public static void createProducts() {
        Products product1 = new Products();
        product1.setItemName("Item 1");
        product1.setItemGroup("Item Group");
        product1.setDescription("Description 1");
        product1.setStock(2);
        product1.setWeight(4.5);
        product1.setVolume(2.0);

        Products product2 = new Products();
        product2.setItemName("Item 2");
        product2.setItemGroup("Item Group");
        product2.setDescription("Description 2");
        product2.setStock(10);
        product2.setWeight(5.0);
        product2.setVolume(3.5);

        productService.save(product1);
        productService.save(product2);
    }
}
