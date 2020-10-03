package project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.service.ItemService;
import project.service.OrderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PagingUtil {

    @Autowired private OrderService orderService;
    @Autowired private ItemService itemService;
    private static final int RECORD_PER_PAGE = 6;

    public List<OrderDTO> getOrdersForPage(Integer page) {
        List<OrderDTO> all = orderService.findAll();
        Collections.sort(all, Comparator.comparingLong(OrderDTO::getId));
        List<OrderDTO> result = new ArrayList<>();
        int upperLimit = getUpperLimit(page, all.size());
        for (int i = (page - 1) * RECORD_PER_PAGE; i < upperLimit; i++) {
            result.add(all.get(i));
        }

        return result;
    }

    public int getNumOfPages(int size) {
        return (int) Math.ceil(size * 1.0 / RECORD_PER_PAGE);
    }

    public List<ItemDTO> getItemsForPage(Integer page) {
        List<ItemDTO> all = itemService.findAll();
        all.sort(Comparator.comparingLong(ItemDTO::getId));
        List<ItemDTO> result = new ArrayList<>();
        int upperLimit = getUpperLimit(page, all.size());
        for (int i = (page - 1) * RECORD_PER_PAGE; i < upperLimit; i++) {
            result.add(all.get(i));
        }

        return result;
    }

    private int getUpperLimit(int page, int size) {
        return size > page * RECORD_PER_PAGE ? page * RECORD_PER_PAGE : size;
    }
}
