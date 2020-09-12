package service;

import dao.OrderDao;
import entity.Orders;
import entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
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

    public List<Orders> findAll() {
        orderDao.openCurrentSession();
        List<Orders> orders = orderDao.findAll();
        orderDao.closeCurrentSession();
        return orders;
    }

    public void delete(Orders order) {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.delete(order);
        orderDao.closeCurrentSessionwithTransaction();
    }

    public void deleteAll() {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.deleteAll();
        orderDao.closeCurrentSessionwithTransaction();
    }

    public void addItem(Long orderId, Products product) {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.addItem(orderId, product);
        orderDao.closeCurrentSessionwithTransaction();
    }

    public void removeItem(Long orderId, Products product) {
        orderDao.openCurrentSessionwithTransaction();
        orderDao.removeItem(orderId, product);
        orderDao.closeCurrentSessionwithTransaction();
    }
}
