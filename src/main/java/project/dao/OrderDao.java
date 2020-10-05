package project.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Order;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderDao extends AbstractDao {
    @Transactional
    public void save(Order entity) {
        getSession().persist(entity);
    }

    @Transactional
    public void update(Order entity) {
        getSession().update(entity);
    }

    @Transactional
    public Order findById(Long id) {
        return getSession().get(Order.class, id);
    }

    @Transactional
    public List<Order> findAll() {
        CriteriaQuery<Order> cq = getSession()
                .getCriteriaBuilder()
                .createQuery(Order.class);
        Root<Order> rootEntry = cq.from(Order.class);
        CriteriaQuery<Order> all = cq.select(rootEntry);

        TypedQuery<Order> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
