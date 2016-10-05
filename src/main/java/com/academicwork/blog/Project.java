package com.academicwork.blog;

public class Project {

    public final long id;
    public final String title;
    public long blogid;

    public Project(long id, String title) {
        this.id = id;
        this.title = title;
        this.blogid = id/5;
    }

}
