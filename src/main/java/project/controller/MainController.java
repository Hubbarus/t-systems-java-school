package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.service.AddressService;
import project.service.ClientService;
import project.service.ItemService;
import project.service.OrderService;

import java.sql.Date;
import java.util.HashMap;

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

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/getOrders")
    public String getOrders() {
        OrderDTO orderDTO = orderService.findById(105L);
        System.out.println(orderDTO);
        return "home";
    }

    @Transactional
    @GetMapping("/crOrders")
    public String createOrders() {
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(3L, 10);
        map.put(2L, 20);
        orderService.createOrderAndSave(4L, 1L, map,
                PaymentEnum.CASH, ShipmentEnum.DOOR_TO_DOOR, true);
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


    @GetMapping("/crItems")
    public String createItems() {
        ItemDTO item = new ItemDTO();
        item.setItemName("T-Shirt");
        item.setItemGroup("Clothes");
        item.setPrice(25.0);
        item.setDescription("White, Linen");
        item.setStock(20);
        item.setWeight(0.4);
        item.setVolume(0.2);

        ItemDTO item1 = new ItemDTO();
        item1.setItemName("Sneakers");
        item1.setItemGroup("Shoes");
        item1.setPrice(50.0);
        item1.setDescription("Black, Nike Air");
        item1.setStock(10);
        item1.setWeight(0.7);
        item1.setVolume(0.3);

        itemService.save(item);
        itemService.save(item1);
        return "home";
    }
}
