package dao;

import entity.Clients;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientDao extends AbstractDao {

    public ClientDao() { }

    public void save(Clients entity) {
        getCurrentSession().save(entity);
    }

    public void update(Clients entity) {
        getCurrentSession().update(entity);
    }

    public Clients findById(int id) {
        Clients client = getCurrentSession().get(Clients.class, id);
        return client;
    }

    public List<Clients> findAll() {
        CriteriaQuery<Clients> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Clients.class);
        Root<Clients> rootEntry = cq.from(Clients.class);
        CriteriaQuery<Clients> all = cq.select(rootEntry);

        TypedQuery<Clients> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Clients entity) {
        getCurrentSession().delete(entity);
    }
}
