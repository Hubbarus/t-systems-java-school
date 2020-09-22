package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.service.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/shop")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAll(Model model) {
        Set<String> set = itemService.getGroupNames();
        model.addAttribute("categories", new ArrayList<>(set));
        return "shop";
    }

    @RequestMapping(value = "/{category}", method = RequestMethod.GET)
    public String showCategory(@PathVariable String category, Model model) {
        model.addAttribute("items", itemService.findByGroup(category));
        return "shop";
    }

    @RequestMapping(value = "/{category}/{id}", method = RequestMethod.GET)
    public String getItem(@PathVariable String category,
                          @PathVariable Long id, Model model) {
        List<ItemDTO> itemByGroup = itemService.findByGroup(category);
        for (ItemDTO item : itemByGroup) {
            if (item.getId() == id) {
                CartDTO cart = new CartDTO();
                cart.setItem(item);
                model.addAttribute("cart", cart);
            }
        }
        return "itemPage";
    }


}
