package org.example.fileanalysis.infrastructure.persistence.jpa.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "homeworks")
public class HomeworkEntity {
    @Id
    @Column(name = "id")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @Column(name = "date")
    private Date dateOfCompletion;

    @Column(name = "task")
    private String task;

    @Column(name = "filename")
    private String filename;

    public HomeworkEntity(){}

    public HomeworkEntity(UUID uuid, UserEntity author, Date dateOfCompletion, String task, String filename) {
        this.uuid = uuid;
        this.author = author;
        this.dateOfCompletion = dateOfCompletion;
        this.task = task;
        this.filename = filename;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTask() {
        return task;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public String getFilename() {return filename;}
}
