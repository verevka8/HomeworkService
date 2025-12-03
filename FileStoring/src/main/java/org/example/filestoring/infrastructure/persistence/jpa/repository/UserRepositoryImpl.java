package org.example.filestoring.infrastructure.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import org.example.filestoring.domain.model.User;
import org.example.filestoring.domain.repository.UserRepository;

import org.example.filestoring.infrastructure.persistence.jpa.entity.HomeworkEntity;
import org.example.filestoring.infrastructure.persistence.jpa.entity.UserEntity;
import org.example.filestoring.infrastructure.persistence.mapper.UserMapper;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManagerFactory emf;
    private final UserMapper mapper;

    public UserRepositoryImpl(UserMapper mapper, EntityManagerFactory emf) {
        this.mapper = mapper;
        this.emf = emf;
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
    public Optional<User> getUserById(UUID uuid) {
        try (EntityManager em = emf.createEntityManager()) {
            UserEntity entity = em.find(UserEntity.class, uuid);
            return Optional.ofNullable(entity).map(mapper::toDomain);
        }
    }

    @Override
    public Optional<User> getUserByName(String firstname, String lastname) {
        try (EntityManager em = emf.createEntityManager()) {
            UserEntity entity = (UserEntity) em.createNativeQuery("select * from users where firstname = ? and lastname = ?").setParameter(1, firstname).setParameter(2, lastname).getSingleResult();
            return Optional.ofNullable(entity).map(mapper::toDomain);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
