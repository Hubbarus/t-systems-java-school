package project.dao;

import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Item;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;

@Repository
@Log
public class ItemDao extends AbstractDao {
    @Transactional
    public void save(Item entity) {
        getSession().persist(entity);
        log.log(Level.INFO, String.format("Item with id %s saved.", entity.getId()));
    }

    @Transactional
    public void update(Item entity) {
        getSession().update(entity);
        log.log(Level.INFO, String.format("Item with id %s updated.", entity.getId()));
    }
    @Transactional
    public Item findById(Long id) {
        return getSession().get(Item.class, id);
    }

    @Transactional
    public List<Item> findAll() {
        CriteriaQuery<Item> cq = getSession()
                .getCriteriaBuilder()
                .createQuery(Item.class);
        Root<Item> rootEntry = cq.from(Item.class);
        CriteriaQuery<Item> all = cq.select(rootEntry);

        TypedQuery<Item> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
    }

    @Transactional
    public List<Item> getItems(int from, int quantity) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);

        Root<Item> rootEntry = cq.from(Item.class);
        CriteriaQuery<Item> all = cq.select(rootEntry).orderBy(cb.asc(rootEntry.get("id")));

        return getSession().createQuery(all)
                .setFirstResult(from)
                .setMaxResults(quantity)
                .list();
    }
}
