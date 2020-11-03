package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.dto.CartDTO;
import project.service.ItemService;
import project.utils.CartListWrapper;

import java.util.ArrayList;

@Controller
@RequestMapping("/cart")

public class CartController {

    @Autowired private ItemService itemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCart(Model model,
                           @SessionAttribute("items") CartListWrapper wrapper) {
        model.addAttribute("items", wrapper);
        return "cart";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addToCart(@ModelAttribute("cart") CartDTO cart,
                            @SessionAttribute("items") CartListWrapper wrapper,
                            Model model) {
        itemService.addToCart(wrapper, cart);
        model.addAttribute("items", wrapper);
        return "cart";
    }

    @RequestMapping(value = "/add")
    public String addOneToCart(@RequestParam("itemId") Long itemId,
                               @SessionAttribute("items") CartListWrapper wrapper,
                               Model model) {
        itemService.buyInOneClick(wrapper, itemId);
        model.addAttribute("items", wrapper);
        return "cart";
    }

    @RequestMapping(value = "/removeItem", method = RequestMethod.GET)
    public String removeItem(@RequestParam(value = "itemId") long itemId, Model model,
                             @SessionAttribute("items") CartListWrapper wrapper) {
        itemService.removeItemFromCart(wrapper, itemId);
        model.addAttribute("items", wrapper);
        return "cart";
    }

    @RequestMapping(value = "/clearCart", method = RequestMethod.GET)
    public String clearCart(@SessionAttribute("items") CartListWrapper wrapper) {
        wrapper.setList(new ArrayList<>());
        return "redirect:/?success=yes";
    }
}
