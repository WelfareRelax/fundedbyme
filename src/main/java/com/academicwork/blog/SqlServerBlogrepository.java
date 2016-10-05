package com.academicwork.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SqlServerBlogrepository implements ProjectRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Project> listBlogs() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, title FROM [dbo].[Project]")) {
            List<Project> blogs = new ArrayList<>();
            while (rs.next()) blogs.add(rsBlog(rs));
            return blogs;
        } catch (SQLException e) {
            throw new ProjectRepositoryException(e);
        }
    }

    @Override
    public Project getProject(long blogId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, title FROM [dbo].[Project] WHERE id = ?")) {
            ps.setLong(1, blogId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new ProjectRepositoryException("No blog with ID " + blogId);
                else return rsBlog(rs);
            }
        } catch (SQLException e) {
            throw new ProjectRepositoryException(e);
        }
    }

    public Project postBlog(String title, long blogid) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [dbo].[Comments] (title, Blog_id) VALUES (?,?)")) {

            ps.setString(1, title);
            ps.setLong(2, blogid);

            int rs = ps.executeUpdate();
            if(rs == 0){

                System.out.println("error is 0");
            }

            return getProject(blogid);



                /*else return rsBlog(rs);*/

    } catch (SQLException e) {
            throw new ProjectRepositoryException(e);
        }
    }



    @Override
    public User getAuthorOf(Project blog) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT u.[UserID], u.[UserName], u.[FirstName], u.[LastName] " +
                     "FROM [dbo].[Users] u JOIN [dbo].[Project] b ON b.User_Id = u.UserID " +
                     "WHERE b.id = ?")) {
            ps.setLong(1, blog.id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new ProjectRepositoryException("No blog with ID " + blog.id);
                else return new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            throw new ProjectRepositoryException(e);
        }
    }

    @Override
    public List<Comment> getEntriesIn(Project blog, long pageid) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT p.Id, p.Title, p.Body, p.Date, p.Blog_Id " +

                     "FROM [dbo].[Comments] p WHERE P.Blog_Id = ? AND p.Id < ? AND p.Id > ?  ORDER BY p.Date DESC")) {
            ps.setLong(1, blog.id);
          ps.setLong(2, pageid);
            ps.setLong(3, pageid);
            try (ResultSet rs = ps.executeQuery()) {
                List<Comment> posts = new ArrayList<>();
                while (rs.next()) posts.add(rsPost(rs));
                return posts;
            }
        } catch (SQLException e) {
            throw new ProjectRepositoryException(e);
        }
    }

    private Comment rsPost(ResultSet rs) throws SQLException {
        return new Comment(
                rs.getLong("Id"),
                rs.getString("Title"),
                rs.getString("Body"),
                rs.getTimestamp("Date").toLocalDateTime(),
                rs.getLong("Blog_Id")
        );
    }

    private Project rsBlog(ResultSet rs) throws SQLException {
        return new Project(rs.getLong("id"), rs.getString("title"));
    }
}
