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
    public Homework getHomeWorkById(UUID uuid) {
        return mapper.toDomain(getHomeworkEntity(uuid));
    }

    public HomeworkEntity getHomeworkEntity(UUID uuid) {
        EntityManager em = emf.createEntityManager();
        return em.find(HomeworkEntity.class, uuid);
    }
}
