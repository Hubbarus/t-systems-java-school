package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dto.AddressDTO;
import project.service.AddressService;

@Controller
@RequestMapping("/")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/crAddresses")
    public String createAddresses() {
        AddressDTO address = new AddressDTO();
        address.setCountry("Russia");
        address.setCity("Spb");
        address.setPostcode("197349");
        address.setStreet("Nevskiy prospect");
        address.setBuilding(20);
        address.setApart(5);

        AddressDTO address1 = new AddressDTO();
        address1.setCountry("USA");
        address1.setCity("Los-Angeles");
        address1.setPostcode("19733124");
        address1.setStreet("Black Str.");
        address1.setBuilding(45);
        address1.setApart(1);

        addressService.save(address);
        addressService.save(address1);
        return "home";
    }
}
