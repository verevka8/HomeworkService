package org.example.fileanalisys.infrastructure.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.fileanalisys.domain.model.User;
import org.example.fileanalisys.domain.repository.UserRepository;

import org.example.fileanalisys.infrastructure.persistence.jpa.entity.UserEntity;
import org.example.fileanalisys.infrastructure.persistence.mapper.UserMapper;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
    private final UserMapper mapper;

    public UserRepositoryImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void saveUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(mapper.toEntity(user));
        } catch (HibernateException e) {
            em.getTransaction().rollback();
        }
        em.getTransaction().commit();
    }

    @Override
    public User getUserById(UUID uuid) {
        return mapper.toDomain(getUserEntity(uuid));
    }

    public UserEntity getUserEntity(UUID uuid) {
        EntityManager em = emf.createEntityManager();
        return em.find(UserEntity.class, uuid);
    }

    @Override
    public User getUserByName(String firstname, String lastname) {
        EntityManager em = emf.createEntityManager();
        UserEntity entity = (UserEntity) em.createNativeQuery("select * from users where firstname = ? and lastname = ?").setParameter(1, firstname).setParameter(2, lastname).getSingleResult();
        return mapper.toDomain(entity);
    }
}
