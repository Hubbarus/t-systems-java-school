package project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.service.ItemService;
import project.service.OrderService;

import java.util.List;

public class PagingUtil {

    private static final int RECORD_PER_PAGE = 6;

    @Autowired private OrderService orderService;
    @Autowired private ItemService itemService;

    public List<OrderDTO> getOrdersForPage(Integer page) {
        int from = (page - 1) * RECORD_PER_PAGE;
        return orderService.getOrders(from, RECORD_PER_PAGE);
    }

    public int getNumOfPages(int size) {
        return (int) Math.ceil(size * 1.0 / RECORD_PER_PAGE);
    }

    public List<ItemDTO> getItemsForPage(Integer page) {
        int from = (page - 1) * RECORD_PER_PAGE;
        return itemService.getItems(from, RECORD_PER_PAGE);
    }
}
