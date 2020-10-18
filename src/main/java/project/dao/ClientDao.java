package project.dao;

import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Client;
import project.entity.Item;
import project.entity.Order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;

@Repository
@Log
public class ClientDao extends AbstractDao {
    @Transactional
    public void save(Client entity) {
        getSession().persist(entity);
        log.log(Level.INFO, String.format("User with username %s created.", entity.getEmail()));
    }

    @Transactional
    public void update(Client entity) {
        getSession().update(entity);
        log.log(Level.INFO, String.format("User with %s information updated", entity.getEmail()));
    }

    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return getSession().get(Client.class, id);
    }

    @Transactional(readOnly = true)
    public List<Client> findByEmail(String email) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);

        Root<Client> rootEntry = cq.from(Client.class);
        CriteriaQuery<Client> clients = cq.select(rootEntry).where(cb.equal(rootEntry.get("email"), email));

        return getSession().createQuery(clients)
                .list();
    }

    @Transactional(readOnly = true)
    public List<Object[]> getTopTenClients() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Order> rootEntry = cq.from(Order.class);
        Join<Order, Item> join = rootEntry.join("client");

        cq.groupBy(join.get("id"));
        cq.multiselect(join.get("id"), cb.count(rootEntry)).orderBy(cb.desc(cb.count(rootEntry)));

        List<Object[]> list = getSession()
                .createQuery(cq)
                .setMaxResults(10)
                .list();
        return list;
    }
}
