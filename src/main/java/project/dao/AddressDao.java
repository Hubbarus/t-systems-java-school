package project.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Address;

@Repository
public class AddressDao extends AbstractDao {

    @Transactional
    public Address findById(Long id) {
        return getSession().get(Address.class, id);
    }
}
