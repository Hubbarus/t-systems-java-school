package dao;

import entity.Address;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AddressDao extends AbstractDao {

    public AddressDao() { }

    public void save(Address entity) {
        getCurrentSession().save(entity);
    }

    public void update(Address entity) {
        getCurrentSession().update(entity);
    }

    public Address findById(int id) {
        Address address = getCurrentSession().get(Address.class, id);
        return address;
    }

    public List<Address> findAll() {
        CriteriaQuery<Address> cq = getCurrentSession()
                .getCriteriaBuilder()
                .createQuery(Address.class);
        Root<Address> rootEntry = cq.from(Address.class);
        CriteriaQuery<Address> all = cq.select(rootEntry);

        TypedQuery<Address> allQuery = getCurrentSession().createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Address entity) {
        getCurrentSession().delete(entity);
    }
}
