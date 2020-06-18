package com.huk;

import com.huk.entities.SongStatisticEntity;
import com.huk.entities.UserEntity;
import com.huk.entities.UserRoleEntity;
import com.huk.enums.UserRole;
import com.huk.services.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;
import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;

@Configuration
@ComponentScan("com.huk.services")
@PropertySource("classpath:application.properties")
public class AppConfiguration {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private  String user;
    @Value("${spring.datasource.password}")
    private String password;


    @Bean(name = "entityManagerFactory")
    public SessionFactory sessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            Properties settings = new Properties();
            settings.put(DRIVER, "org.postgresql.Driver");
            settings.put(URL, "jdbc:postgresql://localhost:5432/content-parser?useSSL=false");
            settings.put(USER, "postgres");
            settings.put(PASS, "352575");
            settings.put(DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(SHOW_SQL, "true");
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(SongStatisticEntity.class);
            configuration.addAnnotatedClass(UserEntity.class);
            configuration.addAnnotatedClass(UserRoleEntity.class);
            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                                                        .build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public WordFunction amountAllWords() {
        return new AmountAllWords();
    }

    @Bean
    public WordFunction amountPopularWord() {
        return new AmountPopularWord();
    }

    @Bean
    public WordFunction amountSameWords() {
        return new AmountSameWords();
    }

    @Bean
    public WordFunction amountUniqueWords() {
        return new AmountUniqueWords();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
