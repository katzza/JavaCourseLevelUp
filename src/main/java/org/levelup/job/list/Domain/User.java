package org.levelup.job.list.Domain;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String lastName;
    private String passport;

    public User(int id, String lastName) {
        this.id = id;
        this.lastName = lastName;
    }

    public User(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public User(int id, String name, String lastName, String passport) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.passport = passport;
    }
}
