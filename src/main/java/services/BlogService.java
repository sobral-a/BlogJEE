package services;

import dao.BlogAccess;
import dao.GenericAccess;
import models.Blog;
import models.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 16/06/2017.
 */

@SessionScoped
public class BlogService implements Serializable
{
    @Inject
    private BlogAccess blogAccess;

    //Add blog in bdd
    public void add(User user, String theme, String title, Date date)
    {
        Blog blog = new Blog();
        blog.setUser(user);
        blog.setTitle(title);
        blog.setTheme(theme);
        blog.setCreationDate(date);
        blog.setIsDeleted(false);
        blogAccess.add(blog);
    }

    //Delete blog by id
    public void delete(Integer id)
    {
        Blog blog = blogAccess.getById(Blog.class, id);
        blog.setIsDeleted(true);
        blogAccess.update(blog);
    }

    //Update a blog
    public void update(Integer id, User user, String theme, String title)
    {
        Blog blog  = blogAccess.getById(Blog.class, id);
        blog.setUser(user);
        blog.setTitle(title);
        blog.setTheme(theme);
        blog.setIsDeleted(false);
        blogAccess.update(blog);
    }

    //Get blog by id
    public Blog getById(Integer id)
    {
        return blogAccess.getById(Blog.class, id);
    }

    //Get all blogs
    public List<Blog> list()
    {
        return blogAccess.getList(false);
    }
}
