package service;

import dao.OrderDao;
import entity.Order;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public void save(Order order) {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.save(order);
        orderDao.closeCurrentSessionwithTransaction();
    }

    public void update(Order order) {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.update(order);
        orderDao.closeCurrentSessionwithTransaction();
    }

    public Order findById(int id) {
        orderDao.openCurrentSession();
        Order order = orderDao.findById(id);
        orderDao.closeCurrentSession();
        return order;
    }
}
