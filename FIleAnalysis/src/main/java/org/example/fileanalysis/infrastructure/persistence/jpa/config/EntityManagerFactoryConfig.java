package org.example.fileanalysis.infrastructure.persistence.jpa.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EntityManagerFactoryConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put("jakarta.persistence.jdbc.url", System.getenv("DB_URL"));
        props.put("jakarta.persistence.jdbc.user", System.getenv("DB_USER"));
        props.put("jakarta.persistence.jdbc.password", System.getenv("DB_PASSWORD"));

        return Persistence.createEntityManagerFactory("Hibernate", props);
    }
}
