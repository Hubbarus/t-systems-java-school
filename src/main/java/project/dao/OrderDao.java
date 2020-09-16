package project.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import project.entity.Order;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderDao extends AbstractDao {
    public OrderDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void save(Order entity) {
        getCurrentSession().save(entity);
    }

    public void update(Order entity) {
        getCurrentSession().update(entity);
    }

    public Order findById(Long id) {
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

    public void deleteAll() {
        List<Order> order = findAll();
        for (Order o : order) {
            delete(o);
        }
    }
    // TODO add item to cart and so on
}
