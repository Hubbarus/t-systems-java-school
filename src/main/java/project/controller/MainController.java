package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.dto.ClientDTO;
import project.service.ClientService;
import project.utils.CartListWrapper;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
@SessionAttributes(value = {"user", "items"})
public class MainController {

    @Autowired private ClientService clientService;

    @GetMapping("/")
    public String getHome(Model model, Principal principal, HttpServletRequest request) {
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



}
