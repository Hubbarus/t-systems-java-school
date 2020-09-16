package project.dao;

import project.entity.Item;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ItemDao extends AbstractDao {

    public ItemDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void save(Item entity) {
        getCurrentSession().save(entity);
    }

    public void update(Item entity) {
        getCurrentSession().update(entity);
    }

    public Item findById(Long id) {
        Item item = getCurrentSession().get(Item.class, id);
        return item;
    }

    public List<Item> findAll() {
        CriteriaQuery<Item> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Item.class);
        Root<Item> rootEntry = cq.from(Item.class);
        CriteriaQuery<Item> all = cq.select(rootEntry);

        TypedQuery<Item> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Item entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteAll() {
        List<Item> all = findAll();
        for (Item p : all) {
            delete(p);
        }
    }
}
