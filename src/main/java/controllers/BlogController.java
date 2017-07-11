package controllers;

import interceptor.Transaction;
import lombok.Getter;
import lombok.Setter;
import models.Blog;
import services.BlogService;
import services.UserService;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.io.Serializable;

/**
 * Created by alexa on 10/07/2017.
 */

@SessionScoped
@Named("blogController")
public class BlogController implements Serializable
{
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String theme;

    @Inject
    private BlogService blogService;

    @Inject
    private UserService userService;

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

    @Transactional
    public void add(Integer id) throws IOException {
        if (theme.isEmpty() || title.isEmpty()) {
            addMessage("Fill all fields");
            return;
        }
        blogService.add(userService.getById(id), theme, title, new Date());
        FacesContext.getCurrentInstance().getExternalContext().redirect("blogs.xhtml");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Blog getBlog()
    {
        return this.blog;
    }

    @Transactional
    public void delete(Blog blog)
    {
        blogService.delete(blog.getId());
    }

    public void addBlogForm() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addBlog.xhtml");
    }
}
