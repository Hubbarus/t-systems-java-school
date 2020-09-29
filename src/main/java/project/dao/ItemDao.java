package project.dao;

import org.springframework.stereotype.Repository;
import project.entity.Item;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ItemDao extends AbstractDao {

    public void save(Item entity) {
        getSession().persist(entity);
    }

    public void update(Item entity) {
        getSession().update(entity);
    }

    public Item findById(Long id) {
        return getSession().get(Item.class, id);
    }

    public List<Item> findAll() {
        CriteriaQuery<Item> cq = getSession()
                .getCriteriaBuilder()
                .createQuery(Item.class);
        Root<Item> rootEntry = cq.from(Item.class);
        CriteriaQuery<Item> all = cq.select(rootEntry);

        TypedQuery<Item> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
