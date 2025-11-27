package org.example.fileanalisys.domain.model;

import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.Date;
import java.util.UUID;

public class Homework {
    private UUID uuid;
    private User author;
    private Date dateOfCompletion;
    private String task;
    private String filename;

    private Homework(UUID uuid, User author, Date dateOfCompletion, String task, String filename) {
        this.uuid = uuid;
        this.author = author;
        this.dateOfCompletion = dateOfCompletion;
        this.task = task;
        this.filename = filename;
    }

    public static Homework createNew(User author, Date dateOfCompletion, String task, String filename) {
        return new Homework(UUID.randomUUID(), author, dateOfCompletion, task, filename);
    }

    public static Homework rehydrate(UUID uuid, User author, Date dateOfCompletion, String task, String filename) {
        return new Homework(uuid, author, dateOfCompletion, task, filename);
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getAuthor() {
        return author;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public String getTask() {
        return task;
    }

    public String getFilename() {
        return filename;
    }

}
