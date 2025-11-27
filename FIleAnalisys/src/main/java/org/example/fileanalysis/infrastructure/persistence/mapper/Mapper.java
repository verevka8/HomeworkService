package org.example.fileanalysis.infrastructure.persistence.mapper;

public interface Mapper<Domain, JpaEntity> {
    JpaEntity toEntity(Domain domain);

    Domain toDomain(JpaEntity jpaEntity);
}
