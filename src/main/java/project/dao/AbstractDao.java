package project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author paulponomarev
 * @version 1.0
 * Abstract DAO Class for all DAO.
 */
@Repository
public abstract class AbstractDao {

    @Autowired private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
