package dao;

import config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractDao {
    HibernateConfig instance = HibernateConfig.getInstance();

    Session currentSession;
    Transaction currentTransaction;

    Session openCurrentSession() {
        currentSession = instance.getSessionFactory().openSession();
        return currentSession;
    }

    Session openCurrentSessionwithTransaction() {
        currentSession = instance.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    void closeCurrentSession() {
        currentSession.close();
    }

    void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
}
