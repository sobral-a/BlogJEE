package controllers;

import interceptor.Transaction;
import lombok.Getter;
import lombok.Setter;
import models.Blog;
import models.Post;
import services.BlogService;
import services.CommentService;
import services.PostService;
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
    /*** GETTER SETTER ***/
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String theme;
    @Getter @Setter
    private String content;

    /*** SERVICE ***/
    @Inject
    private BlogService blogService;
    @Inject
    private UserService userService;
    @Inject
    private PostService postService;

    private Blog blog;

    //Set the blog
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

    //Add a blog in bdd
    @Transactional
    public void add(Integer id) throws IOException {
        if (theme.isEmpty() || title.isEmpty()) {
            addMessage("Fill all fields");
            return;
        }
        blogService.add(userService.getById(id), theme, title, new Date());
        FacesContext.getCurrentInstance().getExternalContext().redirect("blogs.xhtml");
    }

    //Add a post in bdd
    @Transactional
    public void addPost(Integer id) throws IOException {
        if (title.isEmpty() || content.isEmpty()) {
            addMessage("Fill all fields");
            return;
        }
        postService.add(blog ,userService.getById(id), title, content, new Date());
        Blog newBlog = blogService.getById(blog.getId());
        setBlog(newBlog);
    }

    //Error message
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //Get the blog
    public Blog getBlog()
    {
        return this.blog;
    }

    //Delete a blog
    @Transactional
    public void delete(Blog blog) throws IOException
    {
        blogService.delete(blog.getId());
        Blog newBlog = blogService.getById(this.blog.getId());
        this.blog = newBlog;
        FacesContext.getCurrentInstance().getExternalContext().redirect("blogs.xhtml");
    }

    //Go to the add blog form
    public void addBlogForm() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addBlog.xhtml");
    }

    //Delete post
    @Transactional
    public void delete(Post argPost) throws IOException
    {
        postService.delete(argPost.getId());
        Blog newBlog = blogService.getById(argPost.getBlog().getId());
        postService.delete(argPost.getId());
        this.blog = newBlog;
        FacesContext.getCurrentInstance().getExternalContext().redirect("blog.xhtml");
    }

}
