package project.dao;

import org.springframework.stereotype.Repository;
import project.entity.Client;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ClientDao extends AbstractDao {

    public void save(Client entity) {
        getCurrentSession().save(entity);
    }

    public void update(Client entity) {
        getCurrentSession().update(entity);
    }

    public Client findById(Long id) {
        Client client = getCurrentSession().get(Client.class, id);
        return client;
    }

    public List<Client> findAll() {
        CriteriaQuery<Client> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Client.class);
        Root<Client> rootEntry = cq.from(Client.class);
        CriteriaQuery<Client> all = cq.select(rootEntry);

        TypedQuery<Client> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Client entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteAll() {
        List<Client> client = findAll();
        for (Client c : client) {
            delete(c);
        }
    }
}
