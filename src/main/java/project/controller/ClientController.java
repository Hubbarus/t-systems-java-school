package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.dto.OrderDTO;
import project.exception.NoSuchClientException;
import project.service.AddressService;
import project.service.ClientService;
import project.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired private ClientService clientService;
    @Autowired private UserService userService;
    @Autowired private AddressService addressService;

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

    @RequestMapping(value = "/userInfo/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/userInfo/{id}/manage", method=RequestMethod.GET)
    public String manageAccountInfo(@PathVariable Long id, Model model) {
        ClientDTO client = clientService.findById(id);
        model.addAttribute("client", client);
        return "userEdit";
    }

    @RequestMapping(value = "/userInfo/{id}/manage", method=RequestMethod.POST)
    public String editAccountInfo(@PathVariable Long id,
                                  @ModelAttribute ClientDTO client, Model model) {
        try {
            ClientDTO byEmail = clientService.findByEmail(client.getEmail());
            if (byEmail.getId() == client.getId()) {
                throw new NoSuchClientException("This is the same client");
            }
            model.addAttribute("errorMsg", "This login already exist!");
            return manageAccountInfo(id, model);
        } catch (NoSuchClientException e) {
            ClientDTO old = clientService.findById(id);
            client.setAddressList(old.getAddressList());

            clientService.encodePassword(client);
            clientService.update(client);
        }

        return "redirect:/client/userInfo/{id}";
    }

    @RequestMapping(value = "/userInfo/{id}/manageAddress", method=RequestMethod.GET)
    public String editAddressInfo(@PathVariable Long id,
                                    @RequestParam(value = "addressId", required = false) Long addressId,
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

    @RequestMapping(value = "/userInfo/{id}/manageAddress", method=RequestMethod.POST)
    public String editAddressInfo(@PathVariable Long id,
                                  @ModelAttribute AddressDTO addressDTO, Model model) {
        ClientDTO user = clientService.findById(id);
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
        return getUserInfo(id, model);
    }
}
