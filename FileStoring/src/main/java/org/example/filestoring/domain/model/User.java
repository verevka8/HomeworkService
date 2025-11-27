package org.example.filestoring.domain.model;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String firstname;
    private String lastname;


    private User(UUID uuid, String firstname, String lastname) {
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static User createNew(String firstname, String lastname) {
        return new User(UUID.randomUUID(), firstname, lastname);
    }

    public static User rehydrate(UUID uuid, String firstname, String lastname) {
        return new User(uuid, firstname, lastname);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
