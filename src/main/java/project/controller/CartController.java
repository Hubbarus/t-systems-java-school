package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.CartDTO;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private List<CartDTO> items = new ArrayList<>();

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addToCart(@ModelAttribute("cart") CartDTO cart,
                            Model model) {
        items.add(cart);
        model.addAttribute("items", items);
        return "cart";
    }
}
