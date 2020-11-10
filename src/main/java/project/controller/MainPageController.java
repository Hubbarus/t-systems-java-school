package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.service.ClientService;
import project.service.ItemService;
import project.utils.CartListWrapper;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Controller class for root mapping.
 */
@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
@SessionAttributes(value = {"user", "items", "categories", "topTenItems"})
public class MainPageController {

    @Autowired private ClientService clientService;
    @Autowired private ItemService itemService;

    @GetMapping("/")
    public String getHome(Principal principal, HttpServletRequest request) {
        ClientDTO user;
        if (principal != null) {
            user = clientService.findByEmail(principal.getName());
            request.getSession().setAttribute("user", user);
        }
        return "home";
    }

    @ModelAttribute("items")
    public CartListWrapper createShoppingList() {
        CartListWrapper wrapper = new CartListWrapper();
        wrapper.setList(new ArrayList<>());
        return wrapper;
    }

    @ModelAttribute("categories")
    public List<String> getCategories() {
        Set<String> set = itemService.getGroupNames();
        return new ArrayList<>(set);
    }

    @ModelAttribute("topTenItems")
    public List<CartDTO> getTopTen() {
        return itemService.getTopTenItems();
    }



}
