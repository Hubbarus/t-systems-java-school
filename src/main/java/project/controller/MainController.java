package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.ClientDTO;
import project.service.ClientService;

import java.security.Principal;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MainController {

    @Autowired private ClientService clientService;

    @GetMapping("/")
    public String getHome(Model model, Principal principal) {
        ClientDTO user;
        try {
            user = clientService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        } catch (NullPointerException e) {
            model.addAttribute("user", "Guest");
        }
        return "home";
    }

}
