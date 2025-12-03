package org.example.filestoring.infrastructure.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.filestoring.domain.model.Homework;
import org.example.filestoring.domain.repository.HomeworkRepository;
import org.example.filestoring.infrastructure.persistence.jpa.entity.HomeworkEntity;
import org.example.filestoring.infrastructure.persistence.mapper.HomeworkMapper;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HomeworkRepositoryImpl implements HomeworkRepository {
    private final EntityManagerFactory emf;
    private final HomeworkMapper mapper;

    public HomeworkRepositoryImpl(HomeworkMapper mapper, EntityManagerFactory emf) {
        this.mapper = mapper;
        this.emf = emf;
    }

    @Override
    public void saveHomeWork(Homework homeWork) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(mapper.toEntity(homeWork));
        } catch (HibernateException e) {
            em.getTransaction().rollback();
        }
        em.getTransaction().commit();
    }

    @Override
    public Optional<Homework> getHomeWorkById(UUID uuid) {
        try (EntityManager em = emf.createEntityManager()) {
            HomeworkEntity entity = em.find(HomeworkEntity.class, uuid);
            return Optional.ofNullable(entity).map(mapper::toDomain);
        }
    }
}

