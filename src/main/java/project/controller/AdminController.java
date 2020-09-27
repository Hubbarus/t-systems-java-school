package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.service.OrderService;
import project.utils.StatByDateHolder;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
public class AdminController {

//    private static final String PATH = "admin/";
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAdminPage(Model model) {
        return "admin/adminPage";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getAllOrders(Model model) {
        List<OrderDTO> all = orderService.findAll();
        model.addAttribute("orders", all);
        model.addAttribute("thisOrder", new OrderDTO());
        return "admin/orders";
    }

    @RequestMapping(value = "/orders/edit", method = RequestMethod.POST)
    public String editOrder(@ModelAttribute OrderDTO order, Model model) {
        OrderDTO updtOrder = orderService.findById(order.getId());
        updtOrder.setStatus(order.getStatus());
        orderService.update(updtOrder);
        return getAllOrders(model);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String getStatisticsPage(Model model, StatByDateHolder holder) {
        List<CartDTO> topTenItems = orderService.getTopTenItems();
        List<Map.Entry<ClientDTO, Integer>> topTenClients = orderService.getTopTenClients();

        model.addAttribute("topItems", topTenItems);
        model.addAttribute("topClients", topTenClients);
        if (holder == null) {
            holder = new StatByDateHolder();
        }
        model.addAttribute("statDateForm", holder);
        return "admin/statistics";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public String getDatesStats(@ModelAttribute StatByDateHolder holder, Model model) {
        holder = orderService.getSalesBetweenDates(holder);
        if (holder.getOrders().size() == 0) {
            model.addAttribute("err", "No sales in this period");
        }
        return getStatisticsPage(model, holder);
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String getItemsEditPage(Model model) {
        model.addAttribute("item", new ItemDTO());
        return "admin/items";
    }
}
