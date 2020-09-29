package project.dao;

import org.springframework.stereotype.Repository;
import project.entity.Address;

@Repository
public class AddressDao extends AbstractDao {

    public void update(Address entity) {
        getSession().update(entity);
    }

    public Address findById(Long id) {
        return getSession().get(Address.class, id);
    }
}
