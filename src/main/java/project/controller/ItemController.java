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
        item.setItemName("Trousers");
        item.setItemGroup("Clothes");
        item.setPrice(BigDecimal.valueOf(666.0));
        item.setDescription("White, Leather");
        item.setStock(100);
        item.setWeight(0.3);
        item.setVolume(0.1);

        ItemDTO item1 = new ItemDTO();
        item1.setItemName("Hat");
        item1.setItemGroup("Clothes");
        item1.setPrice(BigDecimal.valueOf(150.5));
        item1.setDescription("Panama, Straw");
        item1.setStock(20);
        item1.setWeight(0.7);
        item1.setVolume(0.3);

        itemService.save(item);
        itemService.save(item1);
        return "home";
    }
}
