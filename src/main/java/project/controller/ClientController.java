package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.dto.OrderDTO;
import project.service.AddressService;
import project.service.ClientService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Controller class for "/client" mapping.
 */
@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired private ClientService clientService;
    @Autowired private AddressService addressService;

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("clientForm", new ClientDTO());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addClient(@Valid @ModelAttribute("userForm") ClientDTO client,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("client", client);
            return "registration";
        }

        if (clientService.checkIfUserExistsAndCreate(client)) {
            model.addAttribute("userNameError", "This username already exist!");
            return "registration";
        } else {
            model.addAttribute("user", new ClientDTO());
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/userInfo")
    public String getUserInfo(Model model, Principal principal) {
        ClientDTO client = clientService.findByEmail(principal.getName());
        model.addAttribute("client", client);
        model.addAttribute("address", new AddressDTO());
        return "/userInfo";
    }

    @GetMapping(value = "/userInfo/manage")
    public String manageAccountInfo(Principal principal, Model model) {
        ClientDTO client = clientService.findByEmail(principal.getName());
        model.addAttribute("client", client);
        return "userEdit";
    }

    @PostMapping(value = "/userInfo/manage")
    public String editAccountInfo(Principal principal,
                                  @Valid @ModelAttribute ClientDTO client,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("client", client);
            return "userEdit";
        }
        model.addAttribute("successMsg", "Information has been changed successfully");
        clientService.updateUserInformation(client);
        return getUserInfo(model, principal);
    }

    @GetMapping(value = "/userInfo/addAddress")
    public String addAddressInfo(@ModelAttribute AddressDTO addressDTO,
                                  Model model) {
        model.addAttribute("address", new AddressDTO());
        return "manageAddress";
    }

    @GetMapping(value = "/userInfo/editAddress")
    public String editAddressInfo(@ModelAttribute AddressDTO addressDTO,
                                  Model model) {
        AddressDTO address = addressService.findById(addressDTO.getId());
        model.addAttribute("address", address);
        return "manageAddress";
    }

    @PostMapping(value = "/userInfo/manageAddress")
    public String editAddressInfo(Principal principal,
                                  @Valid @ModelAttribute("address") AddressDTO addressDTO,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("address", addressDTO);
            return "manageAddress";
        }

        clientService.updateAddressInformation(principal, addressDTO);
        model.addAttribute("successMsg", "Address information has been changed successfully");
        return getUserInfo(model, principal);
    }

    @GetMapping(value = "/userInfo/orders")
    public String viewAllOrders(Model model, Principal principal) {
        ClientDTO user = clientService.findByEmail(principal.getName());
        List<OrderDTO> currentClientOrders = clientService.getAllClientOrders(user);

        model.addAttribute("user", user);
        model.addAttribute("orders", currentClientOrders);
        return "orders";
    }
}
