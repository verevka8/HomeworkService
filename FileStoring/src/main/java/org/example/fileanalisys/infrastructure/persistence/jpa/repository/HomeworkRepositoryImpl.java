package org.example.fileanalisys.infrastructure.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.fileanalisys.domain.model.Homework;
import org.example.fileanalisys.domain.repository.HomeworkRepository;
import org.example.fileanalisys.infrastructure.persistence.jpa.entity.HomeworkEntity;
import org.example.fileanalisys.infrastructure.persistence.mapper.HomeworkMapper;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class HomeworkRepositoryImpl implements HomeworkRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
    private final HomeworkMapper mapper;

    public HomeworkRepositoryImpl(HomeworkMapper mapper) {
        this.mapper = mapper;
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
