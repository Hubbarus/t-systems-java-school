package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MainController {

    @GetMapping("/h")
    public String getHome() {
        System.out.println("____________________________________________________________________________");
        return "home";
    }

    @GetMapping("/")
    public String get() {
        System.out.println("----------------------------------------------------------");
        return "home";
    }
}
