package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.dto.OrderDTO;
import project.entity.enums.PaymentEnum;
import project.entity.enums.ShipmentEnum;
import project.service.OrderService;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    public String getOrders() {
        OrderDTO orderDTO = orderService.findById(105L);
        System.out.println(orderDTO);
        return "home";
    }

    @GetMapping("/remOrders")
    public String remOrders(@RequestParam(name = "id") long id) {
        OrderDTO orderDTO = orderService.findById(id);
        orderService.delete(orderDTO);
        return "home";
    }

    @Transactional
    @GetMapping("/crOrders")
    public String createOrders() {
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(1L, 10);
        map.put(2L, 15);
        orderService.createOrderAndSave(1L, 1L, map,
                PaymentEnum.CARD, ShipmentEnum.SELF_PICKUP, true);


        map = new HashMap<>();
        map.put(5L, 20);
        map.put(4L, 35);
        orderService.createOrderAndSave(2L, 6L, map,
                PaymentEnum.CASH, ShipmentEnum.DOOR_TO_DOOR, false);

        map = new HashMap<>();
        map.put(6L, 5);
        map.put(7L, 1);
        orderService.createOrderAndSave(6L, 2L, map,
                PaymentEnum.REMITTANCE, ShipmentEnum.DOOR_TO_DOOR, false);
        return "home";
    }
}
