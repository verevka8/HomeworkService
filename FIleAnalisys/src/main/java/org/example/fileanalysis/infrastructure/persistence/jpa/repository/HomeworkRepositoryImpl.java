package org.example.fileanalysis.infrastructure.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.fileanalysis.domain.model.Homework;
import org.example.fileanalysis.domain.repository.HomeworkRepository;
import org.example.fileanalysis.infrastructure.persistence.jpa.entity.HomeworkEntity;
import org.example.fileanalysis.infrastructure.persistence.mapper.Mapper;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class HomeworkRepositoryImpl implements HomeworkRepository {
    private final EntityManagerFactory emf;
    private final Mapper<Homework, HomeworkEntity> mapper;

    public HomeworkRepositoryImpl(Mapper<Homework, HomeworkEntity> mapper, EntityManagerFactory emf) {
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

    @Override
    public List<Homework> getHomeworksByTask(String task) {
        EntityManager em = emf.createEntityManager();
        List<HomeworkEntity> list =  em.createQuery("select h from HomeworkEntity h where h.task = :task", HomeworkEntity.class).setParameter("task",task).getResultList();
        List<Homework> result = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i){
            result.add(mapper.toDomain(list.get(i)));
        }
        return result;
    }

    public HomeworkEntity getHomeworkEntity(UUID uuid) {
        EntityManager em = emf.createEntityManager();
        return em.find(HomeworkEntity.class, uuid);
    }
}
