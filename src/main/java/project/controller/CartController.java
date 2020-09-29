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
import project.dto.ItemDTO;
import project.service.ItemService;
import project.utils.CartListWrapper;

import java.util.ArrayList;
import java.util.List;

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
        List<CartDTO> cartDTOList = wrapper.getList();
        cartDTOList.add(cart);
        wrapper.setList(cartDTOList);
        return showCart(model, wrapper);
    }

    @RequestMapping(value = "/add")
    public String addOneToCart(@RequestParam("itemId") Long itemId,
                               @SessionAttribute("items") CartListWrapper wrapper, Model model) {
        List<CartDTO> cartDTOList = wrapper.getList();
        ItemDTO item = itemService.findById(itemId);
        CartDTO cart = new CartDTO();
        cart.setItem(item);
        cart.setQuantity(1);
        cartDTOList.add(cart);
        wrapper.setList(cartDTOList);
        return showCart(model, wrapper);
    }

    @RequestMapping(value = "/removeItem", method = RequestMethod.GET)
    public String removeItem(@RequestParam(value = "itemId") long itemId, Model model,
                             @SessionAttribute("items") CartListWrapper wrapper) {
        List<CartDTO> items = wrapper.getList();
        for (CartDTO cartDTO : items) {
            if (cartDTO.getItem().getId() == itemId) {
                items.remove(cartDTO);
                break;
            }
        }
        wrapper.setList(items);
        return showCart(model, wrapper);
    }

    @RequestMapping(value = "/clearCart", method = RequestMethod.GET)
    public String clearCart(@SessionAttribute("items") CartListWrapper wrapper) {
        wrapper.setList(new ArrayList<>());
        return "redirect:/?success=yes";
    }
}
