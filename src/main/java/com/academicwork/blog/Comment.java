package com.academicwork.blog;

import java.time.LocalDateTime;

public class Comment {

    public final long id;
    public final long blogId;
    public final String title;
    public final String body;
    public final LocalDateTime entryDate;

    public Comment(long id, String title, String body, LocalDateTime entryDate, long blogId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.entryDate = entryDate;
        this.blogId = blogId;
    }
}
