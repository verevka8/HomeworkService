package org.example.fileanalysis.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "plagiarism",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_plagiarism_user1_user2_task",
                columnNames = {"user1_id", "user2_id", "task"}
        ))
public class PlagiarismEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private UserEntity user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private UserEntity user2;

    @Column(name = "task")
    private String task;

    public PlagiarismEntity() {
    }

    public PlagiarismEntity(Long id, UserEntity user1, UserEntity user2, String task) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getUser2() {
        return user2;
    }

    public UserEntity getUser1() {
        return user1;
    }

    public String getTask() {
        return task;
    }
}
