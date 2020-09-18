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
        map.put(1L, 30);
        map.put(2L, 10);
        orderService.createOrderAndSave(1L, 4L, map,
                PaymentEnum.REMITTANCE, ShipmentEnum.SELF_PICKUP, false);
        return "home";
    }
}
