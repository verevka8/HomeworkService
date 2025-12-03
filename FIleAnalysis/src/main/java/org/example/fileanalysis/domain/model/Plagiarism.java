package org.example.fileanalysis.domain.model;

public class Plagiarism {
    private User user1;
    private User user2;
    private String task;

    public Plagiarism(User user1, User user2, String task) {
        this.user1 = user1;
        this.user2 = user2;
        this.task = task;
    }

    public User getUser2() {
        return user2;
    }

    public User getUser1() {
        return user1;
    }

    public String getTask() {
        return task;
    }
}
