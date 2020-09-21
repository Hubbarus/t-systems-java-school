package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dto.AddressDTO;
import project.service.AddressService;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/crAddresses")
    public String createAddresses() {
        AddressDTO address = new AddressDTO();
        address.setCountry("Ukraine");
        address.setCity("Lviv");
        address.setPostcode("148832");
        address.setStreet("Lenina str");
        address.setBuilding(15);
        address.setApart(2);

        AddressDTO address1 = new AddressDTO();
        address1.setCountry("Germany");
        address1.setCity("Berlin");
        address1.setPostcode("155223");
        address1.setStreet("Zoidberg Strasse");
        address1.setBuilding(42);
        address1.setApart(66);

        addressService.save(address);
        addressService.save(address1);
        return "home";
    }
}
