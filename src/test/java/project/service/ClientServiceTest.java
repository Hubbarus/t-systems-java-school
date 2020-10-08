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
import project.dao.ClientDao;
import project.dto.AddressDTO;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.service.utils.TestHelper;
import project.utils.CartListWrapper;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks ClientService clientService;
    @Mock ClientDao clientDao;
    @Mock BCryptPasswordEncoder passwordEncoder;
    @Spy ModelMapper mapper;
    @Mock OrderService orderService;
    @Mock Principal principal;

    private ClientDTO defaultClient = new ClientDTO();

    @Before
    public void setUp() {
        defaultClient = TestHelper.getClient(1, "FirstName", "LastName", "test@gmail.com", new HashSet<>());
        when(clientDao.findAll()).thenReturn(TestHelper.getClientList());
        when(orderService.findAll()).thenReturn(TestHelper.getOrders());
        when(principal.getName()).thenReturn(defaultClient.getEmail());
        doNothing().when(clientDao).save(any());
    }

    @Test
    public void findByEmail() {
        AddressDTO address1 = TestHelper.getAddress(1, 1);
        AddressDTO address2 = TestHelper.getAddress(2, 2);
        ClientDTO client1 = TestHelper.getClient(1, "FirstName1", "LastName1",
                "test1@gmail.com", Set.of(address1, address2));
        ClientDTO client2 = TestHelper.getClient(2, "FirstName2", "LastName2",
                "test2@gmail.com", Set.of(address1, address2));

        ClientDTO target = clientService.findByEmail(client1.getEmail());

        assertEquals(target.getFirstName(), client1.getFirstName());
    }

    @Test
    public void checkIfUserExistsAndCreate() {
        boolean target = clientService.checkIfUserExistsAndCreate(defaultClient);
        assertTrue(target);

        ClientDTO clientDTO = new ClientDTO();
        target = clientService.checkIfUserExistsAndCreate(clientDTO);
        assertFalse(target);
    }

    @Test
    public void getAllClientOrders() {
        ItemDTO item1 = TestHelper.getItem(1, "Item1", "Group1");
        ItemDTO item2 = TestHelper.getItem(2, "Item2", "Group1");

        CartDTO cart1 = TestHelper.getCart(1, item1, 3, null);
        CartDTO cart2 = TestHelper.getCart(2, item2, 4, null);

        OrderDTO order = TestHelper.getOrder(1, "orderNum", defaultClient, List.of(cart1, cart2));
        cart1.setOrder(order);
        cart2.setOrder(order);

        List<OrderDTO> allClientOrders = clientService.getAllClientOrders(defaultClient);

        assertTrue(allClientOrders.contains(order));


    }

    @Test
    public void collectOrder() {
        ItemDTO item1 = TestHelper.getItem(1, "Item1", "Group1");
        ItemDTO item2 = TestHelper.getItem(2, "Item2", "Group1");

        CartDTO cart1 = TestHelper.getCart(1, item1, 3, null);
        CartDTO cart2 = TestHelper.getCart(2, item2, 4, null);

        CartListWrapper wrapper = new CartListWrapper();
        wrapper.setList(List.of(cart1, cart2));
        wrapper.setSubtotal(BigDecimal.valueOf(42));

        OrderDTO target = clientService.collectOrder(wrapper, principal);

        assertEquals(target.getItems(), wrapper.getList());
        assertEquals(target.getClient().getId(), defaultClient.getId());
    }
}