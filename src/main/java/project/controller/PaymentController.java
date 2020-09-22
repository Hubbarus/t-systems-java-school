package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.CartDTO;

@Controller
@RequestMapping("/pay")
public class PaymentController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String doPayment(@ModelAttribute("cart") CartDTO cart, Model model) {

        return "shop";
    }
}
