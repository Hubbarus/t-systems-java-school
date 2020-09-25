package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.OrderDTO;
import project.entity.enums.PaymentEnum;
import project.service.AddressService;
import project.service.ClientService;
import project.service.OrderService;
import project.utils.CartListWrapper;

import java.security.Principal;

@Controller
@RequestMapping("/pay")
public class PaymentController {

    @Autowired private OrderService orderService;
    @Autowired private ClientService clientService;
    @Autowired private AddressService addressService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String chooseExtraDetails(@ModelAttribute("items") CartListWrapper items, Model model, Principal principal) {
        OrderDTO order = new OrderDTO();
        order.setItems(items.getList());
        order.setClient(clientService.findByEmail(principal.getName()));
        order.setSubtotal(items.getSubtotal());
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "details";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String doPayment(@ModelAttribute("order") OrderDTO order, Model model, Principal principal) {
        order.setClient(clientService.findByEmail(principal.getName()));
        if (order.getPaymentMethod().equals(PaymentEnum.CASH.getValue())) {
            order.setPaymentStatus(false);
        }

        orderService.createOrderAndSave(order);

        return "redirect:/cart/clearCart";
    }
}
