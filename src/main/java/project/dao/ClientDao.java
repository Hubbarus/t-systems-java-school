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
        getSession().persist(entity);
    }

    public void update(Client entity) {
        getSession().update(entity);
    }

    public void delete(Client entity) {
        getSession().delete(entity);
    }

    public Client findById(Long id) {
        return getSession().get(Client.class, id);
    }

    public List<Client> findAll() {
        CriteriaQuery<Client> cq = getSession()
                .getCriteriaBuilder()
                .createQuery(Client.class);
        Root<Client> rootEntry = cq.from(Client.class);
        CriteriaQuery<Client> all = cq.select(rootEntry);

        TypedQuery<Client> allQuery = getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
