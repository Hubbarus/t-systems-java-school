package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.converter.ItemConverter;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Item;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.entity.enums.StatusEnum;
import project.service.AddressService;
import project.service.CartService;
import project.service.ClientService;
import project.service.ItemService;
import project.service.OrderService;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MainController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemConverter itemConverter;

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/crOrders")
    public String createOrders() {
        List<ItemDTO> all = itemService.findAll();
        List<Item> allEnt = all.stream().map(it -> itemConverter.convertToEntity(it)).collect(Collectors.toList());
        HashMap<Item, Integer> map = new HashMap<>();
        for (Item item : allEnt) {
            map.put(item, 5);
        }

        OrderDTO order = new OrderDTO();
        order.setClient(clientService.findById(1L));
        order.setAddress(addressService.findById(2L));
        order.setStatus(StatusEnum.NEW);
        order.setPaymentMethod(PaymentEnum.CARD);
        order.setPaymentStatus(true);
        order.setShipmentMethod(ShipmentEnum.DOOR_TO_DOOR);

        order.setItems(map);

        orderService.save(order);
        return "home";
    }

    @GetMapping("/crAddresses")
    public String createAddresses() {
        AddressDTO address = new AddressDTO();
        address.setCountry("Russia");
        address.setCity("Spb");
        address.setPostcode("197349");
        address.setStreet("Nevskiy prospect");
        address.setBuilding(20);
        address.setApart(5);

        AddressDTO address1 = new AddressDTO();
        address1.setCountry("USA");
        address1.setCity("Los-Angeles");
        address1.setPostcode("19733124");
        address1.setStreet("Black Str.");
        address1.setBuilding(45);
        address1.setApart(1);

        addressService.save(address);
        addressService.save(address1);
        return "home";
    }

    @GetMapping("/crClients")
    public String createClients() {
        ClientDTO client = new ClientDTO();
        client.setFirstName("Paul");
        client.setLastName("Ponomarev");
        client.setUsername("username1");
        client.setUserPass("password1");
        client.setEmail("paul@gmail.com");
        client.setBirthDate(Date.valueOf("1990-11-15"));

        ClientDTO client1 = new ClientDTO();
        client1.setFirstName("Ivan");
        client1.setLastName("Petrov");
        client1.setUsername("username2");
        client1.setUserPass("password2");
        client1.setEmail("ivan@gmail.com");
        client1.setBirthDate(Date.valueOf("1995-02-01"));


        clientService.save(client);
        clientService.save(client1);
        return "home";
    }
}
