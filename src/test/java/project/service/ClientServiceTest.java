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
import project.service.utils.TestHelper;

import static org.junit.Assert.assertEquals;
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
        TestHelper.initAddresses();
        TestHelper.initItems();
        TestHelper.initClients();
        TestHelper.initCarts();
        TestHelper.initOrders();
        when(clientDao.findAll()).thenReturn(TestHelper.getAllClients());
        doNothing().when(clientDao).update(any(Client.class));
    }

    @Test
    public void findByEmailWithExistingUser() {
        Client expected = TestHelper.client1;
        expected.setEmail("test1@email.com");
        ClientDTO actual = clientService.findByEmail("test1@email.com");
        assertEquals(expected.getId(), actual.getId());
    }

    @Test(expected = NoSuchClientException.class)
    public void findByEmailWithNotExistingUser() {
        clientService.findByEmail("notexisting@email.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserInformationWithNullFirstArgument() {
        ClientDTO client = mapper.map(TestHelper.client1, ClientDTO.class);
        clientService.updateUserInformation(null, client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserInformationWithNullSecondArgument() {
        ClientDTO client = mapper.map(TestHelper.client1, ClientDTO.class);
        clientService.updateUserInformation(client, null);
    }

    @Test
    public void updateUserInformationWithProperArguments() {
        ClientDTO clientToBeUpdated = mapper.map(TestHelper.client1, ClientDTO.class);
        ClientDTO newClient = mapper.map(TestHelper.client2, ClientDTO.class);

        clientService.updateUserInformation(clientToBeUpdated, newClient);

        assertEquals(clientToBeUpdated.getFirstName(), newClient.getFirstName());
        assertEquals(clientToBeUpdated.getLastName(), newClient.getLastName());
    }
}