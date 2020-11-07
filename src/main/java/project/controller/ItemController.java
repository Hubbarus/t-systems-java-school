package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.service.ItemService;

/**
 * Controller class for "/shop" mapping.
 */
@Controller
@RequestMapping("/shop")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/")
    public String showAll(Model model) {
        return "shop";
    }

    @GetMapping(value = "/{category}")
    public String showCategory(@PathVariable String category, Model model) {
        model.addAttribute("itemsCat", itemService.findByGroup(category));
        return "shop";
    }

    @GetMapping(value = "/{category}/{id}")
    public String getItem(@PathVariable String category,
                          @PathVariable Long id, Model model) {
        model.addAttribute("cart", itemService.getItemInCategoryById(id, category));
        return "itemPage";
    }


}
