package project.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.converter.OrderConverter;
import project.dao.OrderDao;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.entity.Order;
import project.service.utils.TestHelper;
import project.utils.StatByDateHolder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks private OrderService orderService;
    @Mock private OrderDao dao;
    @Mock private OrderConverter converter;

    @Before
    public void setUp() throws Exception {
        when(dao.findAll()).thenReturn(TestHelper.getAllOrders());
        Order order1 = TestHelper.getOrder1();
        when(converter.convertToDTO(any())).thenReturn(TestHelper.convertToDTO(order1));

    }

    @Test
    public void getTopTenItems() {
        List<CartDTO> list = orderService.getTopTenItems();
        assertEquals(4, list.get(0).getQuantity());
        assertEquals(2, list.get(1).getQuantity());
    }

    @Test
    public void getTopTenClients() {
        List<Map.Entry<ClientDTO, Integer>> topTenClients = orderService.getTopTenClients();
        assertEquals(2, (int) topTenClients.get(0).getValue());
    }

    @Test
    public void getSalesBetweenDates() {
        LocalDate to = LocalDate.of(2020, 1, 3);
        LocalDate from = LocalDate.of(2020, 1, 1);

        StatByDateHolder holder = new StatByDateHolder();
        holder.setFrom(Date.valueOf(from));
        holder.setTo(Date.valueOf(to));

        StatByDateHolder salesBetweenDates = orderService.getSalesBetweenDates(holder);

        assertEquals(2, salesBetweenDates.getOrders().size());

        to = LocalDate.of(2020, 1, 3);
        from = LocalDate.of(2020, 1, 5);
        holder.setTo(Date.valueOf(to));
        holder.setFrom(Date.valueOf(from));

        StatByDateHolder salesBetweenDates1 = orderService.getSalesBetweenDates(holder);

        assertEquals(0, salesBetweenDates1.getOrders().size());
    }
}