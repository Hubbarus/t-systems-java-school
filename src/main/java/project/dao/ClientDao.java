package project.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Client;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ClientDao extends AbstractDao {
    @Transactional
    public void save(Client entity) {
        getSession().persist(entity);
    }

    @Transactional
    public void update(Client entity) {
        getSession().update(entity);
    }

    public void delete(Client entity) {
        getSession().delete(entity);
    }
    @Transactional
    public Client findById(Long id) {
        return getSession().get(Client.class, id);
    }
    @Transactional
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
