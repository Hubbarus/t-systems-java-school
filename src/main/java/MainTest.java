import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.config.HibernateConfig;
import project.config.SpringConfig;
import project.converter.AddressConverter;
import project.converter.ClientConverter;
import project.converter.OrderConverter;
import project.converter.ProductConverter;
import project.entity.Addresses;
import project.entity.Clients;
import project.entity.Orders;
import project.entity.Products;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;
import project.service.AddressService;
import project.service.ClientService;
import project.service.OrderService;
import project.service.ProductService;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MainTest {
    static ProductService productService;
    static ClientService clientService;
    static AddressService addressService;
    static OrderService orderService;
    static ProductConverter productConverter;
    static AddressConverter addressConverter;
    static ClientConverter clientConverter;
    static OrderConverter orderConverter;
    static {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class, HibernateConfig.class);
        productService = context.getBean(ProductService.class);
        clientService = context.getBean(ClientService.class);
        addressService = context.getBean(AddressService.class);
        orderService = context.getBean(OrderService.class);
        productConverter = context.getBean(ProductConverter.class);
        addressConverter = context.getBean(AddressConverter.class);
        clientConverter = context.getBean(ClientConverter.class);
        orderConverter = context.getBean(OrderConverter.class);
    }


    public static void main(String[] args) {
        System.out.println(productService.findById(3L));
    }


    public static void printAllEntities() {
        List<Products> products = productService.findAll();
        List<Clients> clients = clientService.findAll();
        List<Addresses> addresses = addressService.findAll();
        List<Orders> orders = orderService.findAll();

        printAll(products);
        System.out.println();

        printAll(clients);
        System.out.println();

        printAll(addresses);
        System.out.println();

        printAll(orders);
        System.out.println();

    }

    public static void createOrders() {
        Addresses address = addressService.findById(1L);
        Clients client = clientService.findById(1L);

        HashMap<Products, Integer> products = new HashMap<>();
        Products p1 = productService.findById(3L);
        Products p2 = productService.findById(4L);
        products.put(p1, 20);
        products.put(p2, 11);

        Orders order = new Orders();
        order.setAddress(address);
        order.setClient(client);
        order.setPayMethod(PaymentEnum.CASH);
        order.setPayStatus(true);
        order.setShipMethod(ShipmentEnum.DOOR_TO_DOOR);
        order.setStatus(StatusEnum.NEW);
        order.setProducts(products);

        orderService.save(order);

        Addresses address1 = addressService.findById(4L);
        Clients client1 = clientService.findById(4L);

        HashMap<Products, Integer> list = new HashMap<>();
        Products p3 = productService.findById(5L);
        Products p4 = productService.findById(6L);
        list.put(p3, 10);
        list.put(p4, 50);

        Orders order1 = new Orders();
        order1.setAddress(address1);
        order1.setClient(client1);
        order1.setProducts(list);
        order1.setPayMethod(PaymentEnum.CARD);
        order1.setPayStatus(false);
        order1.setStatus(StatusEnum.NEW);
        order1.setShipMethod(ShipmentEnum.SELF_PICKUP);

        orderService.save(order1);
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

        Clients client3 = new Clients();
        client3.setFirstName("Ivan");
        client3.setLastName("Stepanov");
        client3.setBirthDate(Date.valueOf("1990-02-15"));
        client3.setEmail("example1@gmail.com");
        client3.setUserPass("pass1");
        client3.setUsername("user1");

        Clients client4 = new Clients();
        client4.setFirstName("Paul");
        client4.setLastName("Ponomarev");
        client4.setBirthDate(Date.valueOf("1990-11-15"));
        client4.setEmail("example2@gmail.com");
        client4.setUserPass("pass2");
        client4.setUsername("user2");

        clientService.save(client1);
        clientService.save(client2);
        clientService.save(client3);
        clientService.save(client4);
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
        product1.setPrice(55.5);

        Products product2 = new Products();
        product2.setItemName("Item 2");
        product2.setItemGroup("Item Group");
        product2.setDescription("Description 2");
        product2.setStock(10);
        product2.setWeight(5.0);
        product2.setVolume(3.5);
        product2.setPrice(20.0);

        Products product3 = new Products();
        product3.setItemName("Item 3");
        product3.setItemGroup("Item Group 3");
        product3.setDescription("Description 3");
        product3.setStock(16);
        product3.setWeight(5.0);
        product3.setVolume(10.0);
        product3.setPrice(100.0);

        Products product4 = new Products();
        product4.setItemName("Item 4");
        product4.setItemGroup("Item Group");
        product4.setDescription("Description 4");
        product4.setStock(20);
        product4.setWeight(1.7);
        product4.setVolume(0.5);
        product4.setPrice(150.0);

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
    }

    public static void printAll(Collection collection) {
        for (Object o : collection) {
            System.out.println(o);
        }
    }
}
