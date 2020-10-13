package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.OrderDTO;
import project.service.ClientService;
import project.utils.CartListWrapper;

import java.security.Principal;

@Controller
@RequestMapping("/pay")
public class PaymentController {

    @Autowired private ClientService clientService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String chooseExtraDetails(@ModelAttribute("items") CartListWrapper items, Model model, Principal principal) {
        OrderDTO order = clientService.collectOrder(items, principal);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "details";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String doPayment(@ModelAttribute("order") OrderDTO order, Principal principal) {
        clientService.doPayment(principal, order);
        return "redirect:/cart/clearCart";
    }
}
