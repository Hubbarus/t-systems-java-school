package project.service;

import org.junit.After;
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
        when(orderService.findAll()).thenReturn(TestHelper.getOrders());
//        when(clientDao.findByEmail(defaultClient.getEmail())).thenReturn();
        when(principal.getName()).thenReturn(defaultClient.getEmail());
        doNothing().when(clientDao).save(any());
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

    @After
    public void flush() {
        TestHelper.flush();
    }
}