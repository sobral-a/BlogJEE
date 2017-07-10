package controllers;

import models.Blog;
import services.BlogService;
import services.BlogService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
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

    private Blog blog;

    public void setBlog(Blog blog)
    {
        this.blog = blog;
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("blog.xhtml");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Blog getBlog()
    {
        return this.blog;
    }

    public void delete(Integer id)
    {
        blogService.delete(id);
    }

}
