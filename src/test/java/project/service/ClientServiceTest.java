package project.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import project.dao.ClientDao;
import project.dto.ClientDTO;
import project.entity.Client;
import project.exception.NoSuchClientException;
import project.service.utils.EntityFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks ClientService clientService;
    @Mock ClientDao clientDao;
    @Mock BCryptPasswordEncoder passwordEncoder;
    @Spy ModelMapper mapper;
    @Mock Model model;

    @Before
    public void setUp() throws Exception {
        when(clientDao.findAll()).thenReturn(EntityFactory.getAllClients());
        doNothing().when(clientDao).update(any(Client.class));
    }

    @Test
    public void findByEmailWithExistingUser() {
        Client expected = EntityFactory.getClient1();
        assertEquals(expected.getId(), clientService.findByEmail("test1@email.com").getId());
    }

    @Test(expected = NoSuchClientException.class)
    public void findByEmailWithNotExistingUser() {
        clientService.findByEmail("notexisting@email.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserInformationWithNullFirstArgument() {
        clientService.updateUserInformation(null, mapper.map(EntityFactory.getClient1(), ClientDTO.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserInformationWithNullSecondArgument() {
        clientService.updateUserInformation(mapper.map(EntityFactory.getClient1(), ClientDTO.class), null);
    }

    @Test
    public void updateUserInformationWithProperArguments() {
        ClientDTO clientToBeUpdated = mapper.map(EntityFactory.getClient1(), ClientDTO.class);
        ClientDTO newClient = mapper.map(EntityFactory.getClient2(), ClientDTO.class);

        assertNotEquals(clientToBeUpdated.getFirstName(), newClient.getFirstName());
        assertNotEquals(clientToBeUpdated.getLastName(), newClient.getLastName());
        assertNotEquals(clientToBeUpdated.getBirthDate(), newClient.getBirthDate());

        clientService.updateUserInformation(clientToBeUpdated, newClient);

        assertEquals(clientToBeUpdated.getFirstName(), newClient.getFirstName());
        assertEquals(clientToBeUpdated.getLastName(), newClient.getLastName());
        assertEquals(clientToBeUpdated.getBirthDate(), newClient.getBirthDate());
    }

    @Test
    public void checkIfUserExistsAndCreate() {
        ClientDTO client = mapper.map(EntityFactory.getClient1(), ClientDTO.class);
        String s = clientService.checkIfUserExistsAndCreate(client, model);

        assertEquals(s, "registration");

        client.setEmail("notExists@text.com");

        String s1 = clientService.checkIfUserExistsAndCreate(client, model);
        assertEquals(s1, "redirect:/login");
    }
}