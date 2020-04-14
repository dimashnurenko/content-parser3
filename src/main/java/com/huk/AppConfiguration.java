package com.huk;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;
import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;



@Configuration
@ComponentScan("com.huk")
public class AppConfiguration {


    @Bean
    public SessionFactory sessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            Properties settings = new Properties();
            settings.put(DRIVER, "org.postgresql.Driver");
            settings.put(URL, "jdbc:postgresql://localhost:5432/postgres?useSSL=false");
            settings.put(USER, "postgres");
            settings.put(PASS, "Gook7353110");
            settings.put(DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(SHOW_SQL, "true");
            configuration.setProperties(settings);
           // configuration.addAnnotatedClass(StudentEntity.class);
            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                                                        .build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
