package project.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import project.entity.Cart;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CartDao extends AbstractDao {

    public CartDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void save(Cart entity) {
        getCurrentSession().save(entity);
    }

    public void update(Cart entity) {
        getCurrentSession().update(entity);
    }

    public Cart findById(Long id) {
        Cart cart = getCurrentSession().get(Cart.class, id);
        return cart;
    }

    public List<Cart> findAll() {
        CriteriaQuery<Cart> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Cart.class);
        Root<Cart> rootEntry = cq.from(Cart.class);
        CriteriaQuery<Cart> all = cq.select(rootEntry);

        TypedQuery<Cart> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Cart entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteAll() {
        List<Cart> list = findAll();
        for (Cart a : list) {
            delete(a);
        }
    }
}
