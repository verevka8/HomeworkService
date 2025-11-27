package org.example.fileanalysis.infrastructure.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.fileanalysis.domain.model.Plagiarism;
import org.example.fileanalysis.domain.repository.PlagiarismRepository;
import org.example.fileanalysis.infrastructure.persistence.jpa.entity.PlagiarismEntity;
import org.example.fileanalysis.infrastructure.persistence.mapper.Mapper;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

@Repository
public class PlagiarismRepositoryImpl implements PlagiarismRepository {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate");
    private final Mapper<Plagiarism, PlagiarismEntity> mapper;

    public PlagiarismRepositoryImpl(Mapper<Plagiarism, PlagiarismEntity> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void savePlagiarism(Plagiarism plagiarism) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(mapper.toEntity(plagiarism));
        } catch (HibernateException e) {
            em.getTransaction().rollback();
        }
        em.getTransaction().commit();
    }
}
