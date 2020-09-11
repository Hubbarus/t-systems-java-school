package dao;

import entity.Orders;
import entity.Products;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

public class OrderDao extends AbstractDao {
    public OrderDao() { }

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

    public void addItem(Long id, Products product) {
        Orders order = findById(id);
        HashMap<Products, Integer> list = order.getProducts();
        if (list.containsKey(product)) {
            list.put(product, list.get(product) + 1);
        } else {
            list.put(product, 1);
        }
        order.setProducts(list);
        update(order);
    }
}
