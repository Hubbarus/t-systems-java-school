package project.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import project.config.DispatcherConfig;
import project.config.HibernateConfig;
import project.config.SpringConfig;
import project.dto.AddressDTO;
import project.dto.ClientDTO;
import project.entity.enums.RoleEnum;
import project.exception.NoSuchClientException;

import java.security.Principal;
import java.sql.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, HibernateConfig.class, DispatcherConfig.class})
@WebAppConfiguration
public class ClientServiceTest {

    @Autowired ClientService clientService;

    private long clientId;

    public ClientDTO getClient() {
        return ClientDTO.builder()
                .firstName("Expected Name")
                .lastName("Expected Lastname")
                .active(true)
                .birthDate(new Date(new java.util.Date().getTime()))
                .email("expected@test.com")
                .role(RoleEnum.USER)
                .userPass("expectedPass")
                .build();

    }

    @Before
    public void setUp() {
        ClientDTO client = getClient();
        clientService.save(client);
        ClientDTO byEmail = clientService.findByEmail(client.getEmail());
        clientId = byEmail.getId();
    }

    @After
    public void tearDown() {
        ClientDTO byId = clientService.findById(clientId);
        byId.setAddressList(new HashSet<AddressDTO>());
        clientService.update(byId);
        clientService.delete(byId);
    }

    @Test(expected = NoSuchClientException.class)
    public void findByEmailNotExist() {
        clientService.findByEmail("");
    }

    @Test
    public void findByEmailExists() {
        ClientDTO target = getClient();
        ClientDTO actual = clientService.findByEmail("expected@test.com");
        assertEquals("Clients are not equal", target.getFirstName(), actual.getFirstName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserInformationWithNullFirstArgument() {
        clientService.updateUserInformation(null, getClient());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserInformationWithNullSecondArgument() {
        clientService.updateUserInformation(getClient(), null);
    }

    @Test
    public void updateUserInformationWithProperArguments() {
        ClientDTO newInformationClient = ClientDTO.builder()
                .firstName("New Name")
                .lastName("Expected Lastname")
                .active(true)
                .birthDate(new Date(new java.util.Date().getTime()))
                .email("expected@test.com")
                .role(RoleEnum.USER)
                .userPass("expectedPass")
                .build();
        ClientDTO current = clientService.findById(clientId);
        clientService.updateUserInformation(current, newInformationClient);
        ClientDTO target = clientService.findById(clientId);

        assertEquals("Client was not updated successfully", target.getFirstName(), newInformationClient.getFirstName());
    }

    @Test
    public void updateAddressInformation() {
        ClientDTO target = clientService.findById(clientId);

        Principal principal = () -> getClient().getEmail();
        AddressDTO address = AddressServiceTest.getAddress();

        clientService.updateAddressInformation(principal, address);

        ClientDTO result = clientService.findById(clientId);
        assertEquals("Address was not added", (result.getAddressList().size() - 1), target.getAddressList().size());
    }

    @Test
    public void getAllClientOrders() {
    }

    @Test
    public void collectOrder() {
    }
}