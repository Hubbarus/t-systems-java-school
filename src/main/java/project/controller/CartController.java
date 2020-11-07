package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.dto.CartDTO;
import project.service.ItemService;
import project.utils.CartListWrapper;

import java.util.ArrayList;

/**
 * Controller class for "/cart" mapping.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private ItemService itemService;

    @GetMapping(value = "/")
    public String showCart(Model model,
                           @SessionAttribute("items") CartListWrapper wrapper) {
        model.addAttribute("items", wrapper);
        return "cart";
    }

    @PostMapping(value = "/")
    public String addToCart(@ModelAttribute("cart") CartDTO cart,
                            @SessionAttribute("items") CartListWrapper wrapper,
                            Model model) {
        itemService.addToCart(wrapper, cart);
        model.addAttribute("items", wrapper);
        return "cart";
    }

    @GetMapping(value = "/add")
    public String addOneToCart(@RequestParam("itemId") Long itemId,
                               @SessionAttribute("items") CartListWrapper wrapper,
                               Model model) {
        itemService.buyInOneClick(wrapper, itemId);
        model.addAttribute("items", wrapper);
        return "cart";
    }

    @GetMapping(value = "/removeItem")
    public String removeItem(@RequestParam(value = "itemId") long itemId, Model model,
                             @SessionAttribute("items") CartListWrapper wrapper) {
        itemService.removeItemFromCart(wrapper, itemId);
        model.addAttribute("items", wrapper);
        return "cart";
    }

    @GetMapping(value = "/clearCart")
    public String clearCart(@SessionAttribute("items") CartListWrapper wrapper) {
        wrapper.setList(new ArrayList<>());
        return "redirect:/?success=yes";
    }
}
