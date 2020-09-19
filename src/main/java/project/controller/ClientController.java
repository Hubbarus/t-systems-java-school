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
        client.setFirstName("Petr");
        client.setLastName("Sidorov");
        client.setUsername("username3");
        client.setUserPass("password3");
        client.setEmail("petr@gmail.com");
        client.setBirthDate(Date.valueOf("1995-12-07"));

        ClientDTO client1 = new ClientDTO();
        client1.setFirstName("James");
        client1.setLastName("Bond");
        client1.setUsername("username4");
        client1.setUserPass("password4");
        client1.setEmail("james@gmail.com");
        client1.setBirthDate(Date.valueOf("1977-01-15"));


        clientService.save(client);
        clientService.save(client1);
        return "home";
    }
}
