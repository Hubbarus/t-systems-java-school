package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/address")
public class AddressController {

    @GetMapping
    public String getAddressById(@RequestParam(name = "id") Long id, Model model) {
        return "address";
    }
}