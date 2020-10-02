package project.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import project.config.DispatcherConfig;
import project.config.SpringConfig;
import project.config.WebAppInitializer;
import project.dao.ClientDao;
import project.entity.Client;
import project.exception.NoSuchClientException;
import project.service.utils.EntityFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, WebAppInitializer.class, DispatcherConfig.class})
public class ClientServiceTest {

    @Autowired ClientService clientService;
    @MockBean ClientDao clientDao;
    @MockBean BCryptPasswordEncoder passwordEncoder;
    @MockBean OrderService orderService;
    @Autowired ModelMapper mapper;

    @Before
    public void setUp() throws Exception {
        when(clientDao.findAll()).thenReturn(EntityFactory.getAllClients());
        doNothing().when(clientDao).update(any(Client.class));
    }

    @After
    public void tearDown() throws Exception {
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

//    @Test(expected = IllegalArgumentException.class)
//    public void updateUserInformationWithNullFirstArgument() {
//        clientService.updateUserInformation(null, mapper.map(DtoFactory.getClient1(), ClientDTO.class));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void updateUserInformationWithNullSecondArgument() {
//        clientService.updateUserInformation(mapper.map(DtoFactory.getClient1(), ClientDTO.class), null);
//    }

    @Test
    public void updateUserInformationWithNullProperArguments() {

    }

    @Test
    public void updateAddressInformation() {
    }

    @Test
    public void getAllClientOrders() {
    }

    @Test
    public void collectOrder() {
    }

    @Test
    public void checkIfUserExistsAndCreate() {
    }
}