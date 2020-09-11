package config;

import entity.Addresses;
import entity.Clients;
import entity.Orders;
import entity.Products;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    private static HibernateConfig config;

    public static HibernateConfig getInstance() {
        if (config == null) {
            config = new HibernateConfig();
        }
        return config;
    }

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        configuration.addAnnotatedClass(Products.class);
        configuration.addAnnotatedClass(Clients.class);
        configuration.addAnnotatedClass(Orders.class);
        configuration.addAnnotatedClass(Addresses.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;
    }
}
