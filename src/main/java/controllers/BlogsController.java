package controllers;

import models.Blog;
import services.BlogService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alexa on 10/07/2017.
 */

@ApplicationScoped
@Named("blogsController")
public class BlogsController
{
    @Inject
    private BlogService blogService;

    @Transactional
    public List<Blog> getBlogList()
    {
        List<Blog> blogs = blogService.list();

        return blogs;
    }

    public void delete(Integer id)
    {
        blogService.delete(id);
    }

}
