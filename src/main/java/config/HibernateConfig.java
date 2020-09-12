package config;

import entity.Addresses;
import entity.Clients;
import entity.Orders;
import entity.Products;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setAnnotatedClasses(
                Products.class,
                Clients.class,
                Orders.class,
                Addresses.class);
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("0000");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.default_schema",
                "mms_schema");
        hibernateProperties.setProperty(
                "hibernate.dialect",
                "org.hibernate.dialect.PostgresPlusDialect");
        return hibernateProperties;
    }

//    private static HibernateConfig config;
//
//    public static HibernateConfig getInstance() {
//        if (config == null) {
//            config = new HibernateConfig();
//        }
//        return config;
//    }
//
//    public SessionFactory getSessionFactory() {
//        Configuration configuration = new Configuration().configure();
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties());
//        configuration.addAnnotatedClass(Products.class);
//        configuration.addAnnotatedClass(Clients.class);
//        configuration.addAnnotatedClass(Orders.class);
//        configuration.addAnnotatedClass(Addresses.class);
//        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
//
//        return sessionFactory;
//    }
}
