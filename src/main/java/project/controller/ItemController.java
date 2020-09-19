package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dto.ItemDTO;
import project.service.ItemService;

import java.math.BigDecimal;

@Controller
@RequestMapping("/")
public class ItemController {

    @Autowired
    private ItemService itemService;

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
}
