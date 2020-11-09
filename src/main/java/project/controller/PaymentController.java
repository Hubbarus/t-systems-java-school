package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dto.OrderDTO;
import project.exception.AppJsonParseException;
import project.exception.AppQueueException;
import project.exception.OutOfStockException;
import project.service.ClientService;
import project.utils.CartListWrapper;

import java.security.Principal;

/**
 * Controller class for "/pay" mapping.
 */
@Controller
@RequestMapping("/pay")
public class PaymentController {

    @Autowired private ClientService clientService;

    @GetMapping(value = "/")
    public String chooseExtraDetails(@ModelAttribute("items") CartListWrapper items,
                                     Model model, Principal principal) {
        OrderDTO order = clientService.collectOrder(items, principal);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "details";
    }

    @PostMapping(value = "/")
    public String doPayment(@ModelAttribute("order") OrderDTO order,
                            Principal principal) throws AppJsonParseException, AppQueueException, OutOfStockException {
        clientService.doPayment(principal, order);
        return "redirect:/cart/clearCart";
    }
}
