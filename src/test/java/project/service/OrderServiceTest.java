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
import project.service.utils.EntityFactory;
import project.utils.StatByDateHolder;

import java.sql.Date;
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
        when(dao.findAll()).thenReturn(EntityFactory.getAllOrders());
        Order order1 = EntityFactory.getOrder1();
        when(converter.convertToDTO(any())).thenReturn(EntityFactory.convertToDTO(order1));

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
        java.util.Date tomorrow = new java.util.Date();
        tomorrow.setDate(tomorrow.getDate() + 1);

        java.util.Date yesterday = new java.util.Date();
        yesterday.setDate(yesterday.getDate() - 1);

        StatByDateHolder holder = new StatByDateHolder();
        holder.setFrom(new Date(yesterday.getTime()));
        holder.setTo(new Date(tomorrow.getTime()));

        StatByDateHolder salesBetweenDates = orderService.getSalesBetweenDates(holder);

        assertEquals(2, salesBetweenDates.getOrders().size());

        tomorrow.setDate(tomorrow.getDate() - 2);
        yesterday.setDate(yesterday.getDate() - 2);
        holder.setTo(new Date(tomorrow.getTime()));
        holder.setFrom(new Date(yesterday.getTime()));

        StatByDateHolder salesBetweenDates1 = orderService.getSalesBetweenDates(holder);

        assertEquals(0, salesBetweenDates1.getOrders().size());
    }
}