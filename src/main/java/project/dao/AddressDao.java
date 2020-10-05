package project.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Address;

@Repository
public class AddressDao extends AbstractDao {

    @Transactional
    public void update(Address entity) {
        getSession().update(entity);
    }
    @Transactional
    public Address findById(Long id) {
        return getSession().get(Address.class, id);
    }
}
