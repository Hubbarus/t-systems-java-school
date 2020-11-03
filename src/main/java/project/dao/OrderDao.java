package project.dao;

import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Client;
import project.entity.Order;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;

/**
 * Dao class for {@link Order} entity.
 */
@Repository
@Log
public class OrderDao extends AbstractDao {
    @Transactional
    public void save(Order entity) {
        getSession().persist(entity);
        log.log(Level.INFO, String.format("Order with id %s saved.", entity.getId()));
    }

    @Transactional
    public void update(Order entity) {
        getSession().update(entity);
        log.log(Level.INFO, String.format("Order with id %s updated.", entity.getId()));
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return getSession().get(Order.class, id);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        CriteriaQuery<Order> cq = getSession()
                .getCriteriaBuilder()
                .createQuery(Order.class);
        Root<Order> rootEntry = cq.from(Order.class);
        CriteriaQuery<Order> all = cq.select(rootEntry);

        TypedQuery<Order> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Order> getOrders(int from, int quantity) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> rootEntry = cq.from(Order.class);
        CriteriaQuery<Order> all = cq.select(rootEntry).orderBy(cb.asc(rootEntry.get("id")));

        return getSession().createQuery(all)
                .setFirstResult(from)
                .setMaxResults(quantity)
                .list();
    }

    @Transactional(readOnly = true)
    public List<Order> getAllClientOrders(Client user) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> rootEntry = cq.from(Order.class);
        CriteriaQuery<Order> orders = cq.select(rootEntry).where(cb.equal(rootEntry.get("client"), user));

        return getSession().createQuery(orders)
                .list();
    }
}
