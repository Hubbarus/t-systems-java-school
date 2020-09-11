package service;

import dao.OrderDao;
import entity.Orders;
import entity.Products;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public void save(Orders order) {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.save(order);
        orderDao.closeCurrentSessionwithTransaction();
    }

    public void update(Orders order) {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.update(order);
        orderDao.closeCurrentSessionwithTransaction();
    }

    public Orders findById(Long id) {
        orderDao.openCurrentSession();
        Orders order = orderDao.findById(id);
        orderDao.closeCurrentSession();
        return order;
    }

    public void addItem(Long id, Products product) {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.addItem(id, product);
        orderDao.closeCurrentSessionwithTransaction();
    }
}
