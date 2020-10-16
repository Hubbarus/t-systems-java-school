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
import project.converter.OrderConverter;
import project.dao.OrderDao;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Order;
import project.entity.enums.StatusEnum;
import project.service.utils.TestHelper;
import project.utils.OrderNumberGenerator;
import project.utils.StatByDateHolder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks private OrderService orderService;
    @Mock private OrderDao dao;
    @Mock private OrderConverter converter;
    @Spy private OrderNumberGenerator generator;
    @Spy private ModelMapper mapper;
    @Mock private ItemService itemService;

    private OrderDTO defaultOrder = new OrderDTO();
    private OrderDTO anotherOrder = new OrderDTO();
    private ClientDTO defaultClient = new ClientDTO();

    @Before
    public void setUp() {
        defaultClient = TestHelper.getClient(1, "FirstName",
                "LastName", "test@gmail.com", new HashSet<>());
        ItemDTO item = TestHelper.getItem(1, "ItemName", "ItemGroup");
        CartDTO cart = TestHelper.getCart(1, item, 10, defaultOrder);
        ArrayList<CartDTO> carts = new ArrayList<>();
        carts.add(cart);

        defaultOrder = TestHelper.getOrder(1, "number", defaultClient, carts);

        ItemDTO item1 = TestHelper.getItem(2, "ItemName1", "ItemGroup1");
        CartDTO cart1 = TestHelper.getCart(2, item1, 20, null);
        ArrayList<CartDTO> carts1 = new ArrayList<>();
        carts1.add(cart1);

        anotherOrder = TestHelper.getOrder(2, "anotherNum", defaultClient, carts1);
        cart1.setOrder(anotherOrder);

        when(itemService.findById(any())).thenReturn(item);
        doNothing().when(itemService).update(any());
        doNothing().when(dao).save(any());
        when(dao.findAll()).thenReturn(TestHelper.getEntOrders());
        when(converter.convertToDTO(any(Order.class)))
                .thenReturn(TestHelper.convertToDTO(defaultOrder))
                .thenReturn(anotherOrder);
    }

    @Test
    public void createOrderAndSave() {
        assertEquals(StatusEnum.DELIVERED, defaultOrder.getStatus());

        orderService.createOrderAndSave(defaultOrder);
        Date now = new Date(new java.util.Date().getTime());

        assertEquals(StatusEnum.NEW, defaultOrder.getStatus());
        assertEquals(now.toLocalDate(), defaultOrder.getDate().toLocalDate());
    }

    @Test
    public void getSalesBetweenDates() {
        LocalDate from = LocalDate.of(2020, 4, 4);
        LocalDate to = LocalDate.of(2020, 5, 20);
        StatByDateHolder holder = new StatByDateHolder();
        holder.setFrom(from);
        holder.setTo(to);

        orderService.getSalesBetweenDates(holder);
        assertEquals(2, holder.getOrders().size());

        from = LocalDate.of(2000, 1, 1);
        to = LocalDate.of(2000, 1, 2);
        holder = new StatByDateHolder();
        holder.setFrom(from);
        holder.setTo(to);

        orderService.getSalesBetweenDates(holder);
        assertEquals(0, holder.getOrders().size());

    }

    @After
    public void flush() {
        TestHelper.flush();
    }
}