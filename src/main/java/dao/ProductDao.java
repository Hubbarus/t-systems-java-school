package dao;

import entity.Product;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductDao extends AbstractDao {
    public ProductDao() { }

    public void save(Product entity) {
        getCurrentSession().save(entity);
    }

    public void update(Product entity) {
        getCurrentSession().update(entity);
    }

    public Product findById(int id) {
        Product product = getCurrentSession().get(Product.class, id);
        return product;
    }

    public List<Product> findAll() {
        CriteriaQuery<Product> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Product.class);
        Root<Product> rootEntry = cq.from(Product.class);
        CriteriaQuery<Product> all = cq.select(rootEntry);

        TypedQuery<Product> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Product entity) {
        getCurrentSession().delete(entity);
    }
}
