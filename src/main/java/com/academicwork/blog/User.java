package com.academicwork.blog;

public class User {

    public final long id;
    public final String userName;
    public final String firstName;
    public final String lastName;

    public User(long id, String userName, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
