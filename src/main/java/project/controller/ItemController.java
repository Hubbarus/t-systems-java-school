package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.service.ItemService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    private List<CartDTO> items = new ArrayList<>();

    @GetMapping("/shop")
    public String showAll(Model model) {
        Set<String> set = itemService.getGroupNames();
        model.addAttribute("categories", new ArrayList<>(set));
        return "shop";
    }

    @RequestMapping(value = "/shop/{category}", method = RequestMethod.GET)
    public String showCategory(@PathVariable String category, Model model) {
        model.addAttribute("items", itemService.findByGroup(category));
        return "shop";
    }

    @RequestMapping(value = "/shop/{category}/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String addToCart(@ModelAttribute("cart") CartDTO cart,
                            Model model) {
        items.add(cart);
        model.addAttribute("items", items);
        return "cart";
    }

    @GetMapping("/crItems")
    public String createItems() {
        ItemDTO item = new ItemDTO();
        item.setItemName("Glasses");
        item.setItemGroup("Accessorize");
        item.setPrice(BigDecimal.valueOf(42));
        item.setDescription("Black, Wooden");
        item.setStock(2);
        item.setWeight(0.1);
        item.setVolume(0.05);

        ItemDTO item1 = new ItemDTO();
        item1.setItemName("Necklace");
        item1.setItemGroup("Accessorize");
        item1.setPrice(BigDecimal.valueOf(70));
        item1.setDescription("Golden");
        item1.setStock(3);
        item1.setWeight(0.1);
        item1.setVolume(0.04);

        itemService.save(item);
        itemService.save(item1);
        return "home";
    }

    @GetMapping("/shop/pay")
    public String doPayment(@ModelAttribute("cart") CartDTO cart, Model model) {

        return "shop";
    }
}
