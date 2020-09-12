package project.dao;

import project.entity.Orders;
import project.entity.Products;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderDao extends AbstractDao {
    public OrderDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void save(Orders entity) {
        getCurrentSession().save(entity);
    }

    public void update(Orders entity) {
        getCurrentSession().update(entity);
    }

    public Orders findById(Long id) {
        Orders order = getCurrentSession().get(Orders.class, id);
        return order;
    }

    public List<Orders> findAll() {
        CriteriaQuery<Orders> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Orders.class);
        Root<Orders> rootEntry = cq.from(Orders.class);
        CriteriaQuery<Orders> all = cq.select(rootEntry);

        TypedQuery<Orders> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Orders entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteAll() {
        List<Orders> orders = findAll();
        for (Orders o : orders) {
            delete(o);
        }
    }

    public void addItem(Long orderId, Products product) {
        Orders order = findById(orderId);
        HashMap<Products, Integer> list = order.getProducts();
        if (list.containsKey(product)) {
            list.put(product, list.get(product) + 1);
        } else {
            list.put(product, 1);
        }
        order.setProducts(list);
        update(order);
    }

    public void removeItem(Long orderId, Products product) {
        Orders order = findById(orderId);
        HashMap<Products, Integer> list = order.getProducts();
        if (list.containsKey(product)) {
            list.remove(product);
        }
        order.setProducts(list);
        update(order);
    }
}
