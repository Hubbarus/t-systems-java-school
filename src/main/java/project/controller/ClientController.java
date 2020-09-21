package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.entity.enums.RoleEnum;
import project.service.AddressService;
import project.service.ClientService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AddressService addressService;


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("clientForm", new ClientDTO());
        model.addAttribute("addressForm", new AddressDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String addClient(@ModelAttribute("userForm") ClientDTO userForm,
                            @ModelAttribute("addressForm") AddressDTO addressDTO,
                            Model model) {
        try {
            clientService.findByUserName(userForm.getUsername());
            clientService.createUserAndSave(userForm, addressDTO);
        } catch (Exception e) {
            model.addAttribute("userNameError", "This user already exist!");
            return "registration";
        }
        return "redirect:/";
    }


    @GetMapping("/crClients")
    public String createClients() {
        ClientDTO client = new ClientDTO();
        client.setFirstName("Paul");
        client.setLastName("Ponomarev");
        client.setUsername("username1");
        client.setUserPass("password1");
        client.setEmail("paul@gmail.com");
        client.setBirthDate(Date.valueOf("1990-11-15"));
        client.setRole(RoleEnum.ADMIN);
        List<AddressDTO> list = new ArrayList<>();
        list.add(addressService.findById(1L));
        list.add(addressService.findById(2L));
        client.setAddressList(list);

        ClientDTO client1 = new ClientDTO();
        client1.setFirstName("James");
        client1.setLastName("Bond");
        client1.setUsername("username2");
        client1.setUserPass("password2");
        client1.setEmail("james@gmail.com");
        client1.setBirthDate(Date.valueOf("1977-01-15"));
        client1.setRole(RoleEnum.USER);
        list = new ArrayList<>();
        list.add(addressService.findById(6L));
        client1.setAddressList(list);


        clientService.save(client);
        clientService.save(client1);
        return "home";
    }

}
