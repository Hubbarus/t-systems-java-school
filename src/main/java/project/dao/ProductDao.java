package project.dao;

import project.entity.Products;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductDao extends AbstractDao {

    public ProductDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void save(Products entity) {
        getCurrentSession().save(entity);
    }

    public void update(Products entity) {
        getCurrentSession().update(entity);
    }

    public Products findById(Long id) {
        Products product = getCurrentSession().get(Products.class, id);
        return product;
    }

    public List<Products> findAll() {
        CriteriaQuery<Products> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Products.class);
        Root<Products> rootEntry = cq.from(Products.class);
        CriteriaQuery<Products> all = cq.select(rootEntry);

        TypedQuery<Products> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Products entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteAll() {
        List<Products> all = findAll();
        for (Products p : all) {
            delete(p);
        }
    }
}
