package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dto.ClientDTO;
import project.service.ClientService;

import java.sql.Date;

@Controller
@RequestMapping("/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/crClients")
    public String createClients() {
        ClientDTO client = new ClientDTO();
        client.setFirstName("Paul");
        client.setLastName("Ponomarev");
        client.setUsername("username1");
        client.setUserPass("password1");
        client.setEmail("paul@gmail.com");
        client.setBirthDate(Date.valueOf("1990-11-15"));

        ClientDTO client1 = new ClientDTO();
        client1.setFirstName("Ivan");
        client1.setLastName("Petrov");
        client1.setUsername("username2");
        client1.setUserPass("password2");
        client1.setEmail("ivan@gmail.com");
        client1.setBirthDate(Date.valueOf("1995-02-01"));


        clientService.save(client);
        clientService.save(client1);
        return "home";
    }
}
