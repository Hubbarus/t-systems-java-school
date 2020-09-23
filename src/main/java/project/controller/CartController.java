package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.CartDTO;
import project.utils.CartListWrapper;

import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartListWrapper cartListWrapper;
    private ArrayList<CartDTO> items = new ArrayList<>();

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addToCart(@ModelAttribute("cart") CartDTO cart,
                            Model model) {
        items.add(cart);
        cartListWrapper.setList(items);
        model.addAttribute("items", cartListWrapper);
        return "cart";
    }
}
