package controllers;

import models.Blog;
import services.BlogService;
import services.BlogService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by alexa on 10/07/2017.
 */

@ApplicationScoped
@Named("blogController")
public class BlogController
{
    @Inject
    private BlogService blogService;

    public List<Blog> getBlogList()
    {
        List<Blog> blogs = blogService.list();
        return blogs;
    }

    public Blog getBlog(Integer id)
    {
        Blog blog = blogService.getById(id);
        return blog;
    }

    public void delete(Integer id)
    {
        blogService.delete(id);
    }

}
