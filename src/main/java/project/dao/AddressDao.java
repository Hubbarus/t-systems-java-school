package project.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Address;

/**
 * Dao class for {@link Address} entity.
 */
@Repository
public class AddressDao extends AbstractDao {

    /**
     * @param id of entity in database.
     * @return {@link Address} object with pointed id.
     */
    @Transactional(readOnly = true)
    public Address findById(Long id) {
        return getSession().get(Address.class, id);
    }
}
