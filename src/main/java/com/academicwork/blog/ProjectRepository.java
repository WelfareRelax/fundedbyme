package com.academicwork.blog;

import java.util.List;

public interface ProjectRepository {
    List<Project> listBlogs();
    Project getProject(long blogId);
    User getAuthorOf(Project blog);
    List<Comment> getEntriesIn(Project blog, long pageId);
    Project postBlog(String title, long blogid);

}
