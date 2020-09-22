package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.dto.OrderDTO;
import project.exception.NoSuchClientException;
import project.service.ClientService;
import project.service.UserService;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired private ClientService clientService;
    @Autowired private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        OrderDTO order = new OrderDTO();
        order.setClient(new ClientDTO());
        order.setAddress(new AddressDTO());

        model.addAttribute("clientForm", order);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("userForm") OrderDTO orderDTO,
                            Model model) {
        try {
            ClientDTO client = orderDTO.getClient();
            clientService.findByEmail(client.getEmail());
            model.addAttribute("userNameError", "This username already exist!");
            return "registration";
        } catch (NoSuchClientException e) {
            ClientDTO client = orderDTO.getClient();
            AddressDTO address = orderDTO.getAddress();

            clientService.createUserAndSave(client, address);
            model.addAttribute("user", new ClientDTO());
            return "login";
        }
    }

    @RequestMapping(value = "registration/userInfo/{id}", method = RequestMethod.GET)
    public String getUserInfo(@PathVariable Long id, Model model) {
        ClientDTO client = clientService.findById(id);
        model.addAttribute("client", client);
        return "/userInfo";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(Model model) {
        model.addAttribute("user", new ClientDTO());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute ClientDTO user,
                          Model model) {
        UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
        System.out.println(userDetails);
        return "redirect:/";
    }
}
