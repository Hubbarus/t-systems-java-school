package dao;

import entity.Addresses;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AddressDao extends AbstractDao {

    public AddressDao() { }

    public void save(Addresses entity) {
        getCurrentSession().save(entity);
    }

    public void update(Addresses entity) {
        getCurrentSession().update(entity);
    }

    public Addresses findById(Long id) {
        Addresses address = getCurrentSession().get(Addresses.class, id);
        return address;
    }

    public List<Addresses> findAll() {
        CriteriaQuery<Addresses> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Addresses.class);
        Root<Addresses> rootEntry = cq.from(Addresses.class);
        CriteriaQuery<Addresses> all = cq.select(rootEntry);

        TypedQuery<Addresses> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Addresses entity) {
        getCurrentSession().delete(entity);
    }
}
