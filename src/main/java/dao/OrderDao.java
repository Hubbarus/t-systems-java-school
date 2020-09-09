package dao;

import entity.Order;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrderDao extends AbstractDao {
    public OrderDao() { }

    public void save(Order entity) {
        getCurrentSession().save(entity);
    }

    public void update(Order entity) {
        getCurrentSession().update(entity);
    }

    public Order findById(int id) {
        Order order = getCurrentSession().get(Order.class, id);
        return order;
    }

    public List<Order> findAll() {
        CriteriaQuery<Order> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Order.class);
        Root<Order> rootEntry = cq.from(Order.class);
        CriteriaQuery<Order> all = cq.select(rootEntry);

        TypedQuery<Order> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Order entity) {
        getCurrentSession().delete(entity);
    }
}
