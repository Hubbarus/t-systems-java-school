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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired private ClientService clientService;
    @Autowired private UserService userService;
    @Autowired private AddressService addressService;
    @Autowired private OrderService orderService;

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

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String getUserInfo(Model model, Principal principal) {
        ClientDTO client = clientService.findByEmail(principal.getName());
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

    @RequestMapping(value = "/userInfo/manage", method=RequestMethod.GET)
    public String manageAccountInfo(Principal principal, Model model) {
        ClientDTO client = clientService.findByEmail(principal.getName());
        model.addAttribute("client", client);
        return "userEdit";
    }

    @RequestMapping(value = "/userInfo/manage", method=RequestMethod.POST)
    public String editAccountInfo(Principal principal,
                                  @ModelAttribute ClientDTO client, Model model) {
        try {
            ClientDTO byEmail = clientService.findByEmail(client.getEmail());
            if (byEmail.getId() == client.getId()) {
                throw new NoSuchClientException("This is the same client");
            }
            model.addAttribute("errorMsg", "This login already exist!");
            return manageAccountInfo(principal, model);
        } catch (NoSuchClientException e) {
            ClientDTO old = clientService.findByEmail(principal.getName());
            client.setAddressList(old.getAddressList());

            clientService.encodePassword(client);
            clientService.update(client);
        }

        return getUserInfo(model, principal);
    }

    @RequestMapping(value = "/userInfo/manageAddress", method=RequestMethod.GET)
    public String editAddressInfo(@RequestParam(value = "addressId", required = false) Long addressId,
                                  @RequestParam(value = "action") String action,
                                  Model model) {
        AddressDTO address;
        switch (action) {
            case "manage": {
                address = addressService.findById(addressId);
            } break;
            case "add" : {
                address = new AddressDTO();
            } break;
            default: { return "redirect:/"; }
        }
        model.addAttribute("address", address);

        return "manageAddress";
    }

    @RequestMapping(value = "/userInfo/manageAddress", method=RequestMethod.POST)
    public String editAddressInfo(Principal principal,
                                  @ModelAttribute AddressDTO addressDTO, Model model) {
        ClientDTO user = clientService.findByEmail(principal.getName());
        Set<AddressDTO> addresses = user.getAddressList();
        boolean wasAdded = false;
        for (AddressDTO address : addresses) {
            if (address.getId() == addressDTO.getId()) {
                addresses.remove(address);
                addresses.add(addressDTO);
                wasAdded = true;
                break;
            }
        }

        if (!wasAdded) {
            addresses.add(addressDTO);
        }

        user.setAddressList(addresses);
        clientService.update(user);
        return getUserInfo(model, principal);
    }

    @RequestMapping(value = "/userInfo/orders", method = RequestMethod.GET)
    public String viewAllOrders(Model model, Principal principal) {
        List<OrderDTO> currentClientOrders = new ArrayList<>();
        List<OrderDTO> orders = orderService.findAll();
        ClientDTO user = clientService.findByEmail(principal.getName());
        for (OrderDTO order : orders) {
            long clientId = order.getClient().getId();
            if (clientId == user.getId()) {
                currentClientOrders.add(order);
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("orders", currentClientOrders);
        return "orders";
    }
}
