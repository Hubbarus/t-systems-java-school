package project.dao;

import project.entity.Clients;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ClientDao extends AbstractDao {

    public ClientDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void save(Clients entity) {
        getCurrentSession().save(entity);
    }

    public void update(Clients entity) {
        getCurrentSession().update(entity);
    }

    public Clients findById(Long id) {
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

    public void deleteAll() {
        List<Clients> clients = findAll();
        for (Clients c : clients) {
            delete(c);
        }
    }
}
