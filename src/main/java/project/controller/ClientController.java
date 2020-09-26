package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.dto.OrderDTO;
import project.exception.NoSuchClientException;
import project.service.AddressService;
import project.service.ClientService;
import project.service.OrderService;
import project.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired private ClientService clientService;
    @Autowired private UserService userService;
    @Autowired private AddressService addressService;
    @Autowired private OrderService orderService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {

        model.addAttribute("clientForm", new ClientDTO());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("userForm") ClientDTO client,
                            Model model) {
        try {
            clientService.findByEmail(client.getEmail());
            model.addAttribute("userNameError", "This username already exist!");
            return "registration";
        } catch (NoSuchClientException e) {
            clientService.createUserAndSave(client, null);
            model.addAttribute("user", new ClientDTO());
            return "login";
        }
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String getUserInfo(Model model, Principal principal) {
        ClientDTO client = clientService.findByEmail(principal.getName());
        model.addAttribute("client", client);
        model.addAttribute("address", new AddressDTO());
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

    @RequestMapping(value = "/userInfo/manage", method=RequestMethod.GET)
    public String manageAccountInfo(Principal principal, Model model) {
        ClientDTO client = clientService.findByEmail(principal.getName());
        model.addAttribute("client", client);
        return "userEdit";
    }

    @RequestMapping(value = "/userInfo/manage", method=RequestMethod.POST)
    public String editAccountInfo(Principal principal,
                                  @ModelAttribute ClientDTO client, Model model) {


        ClientDTO currentClient = clientService.findByEmail(principal.getName());
        try {
            ClientDTO byEmail = clientService.findByEmail(client.getEmail());
            if (currentClient.getEmail().equals(byEmail.getEmail())) {
                throw new NoSuchClientException("This is the same client");
            }
            model.addAttribute("errorMsg", "This login already exist!");
            return manageAccountInfo(principal, model);
        } catch (NoSuchClientException e) {
            clientService.updateUserInformation(currentClient, client);
        }

        return getUserInfo(model, principal);
    }

    @RequestMapping(value = "/userInfo/manageAddress", method=RequestMethod.GET)
    public String editAddressInfo(@ModelAttribute AddressDTO addressDTO,
                                  @RequestParam(value = "action", required = false) String action,
                                  Model model) {
        AddressDTO address = addressService.getFormFromAction(addressDTO, action);
        model.addAttribute("address", address);

        return "manageAddress";
    }

    @RequestMapping(value = "/userInfo/manageAddress", method=RequestMethod.POST)
    public String editAddressInfo(Principal principal,
                                  @ModelAttribute AddressDTO addressDTO, Model model) {
        clientService.updateAddressInformation(principal, addressDTO);
        return getUserInfo(model, principal);
    }

    @RequestMapping(value = "/userInfo/orders", method = RequestMethod.GET)
    public String viewAllOrders(Model model, Principal principal) {
        ClientDTO user = clientService.findByEmail(principal.getName());
        List<OrderDTO> currentClientOrders = clientService.getAllClientOrders(user);

        model.addAttribute("user", user);
        model.addAttribute("orders", currentClientOrders);
        return "orders";
    }
}
