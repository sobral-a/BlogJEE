package controllers;

import lombok.Getter;
import lombok.Setter;
import models.Blog;
import models.Post;
import services.BlogService;
import services.PostService;
import services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by alexa on 10/07/2017.
 */

@SessionScoped
@Named("postController")
public class PostController implements Serializable
{
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String content;

    @Inject
    private PostService postService;
    @Inject
    private UserService userService;
    @Inject
    private BlogService blogService;

    private Post post;
    private Blog blog;

    public void setPost(Post post)
    {
        this.post = post;
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("post.xhtml");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Post getPost()
    {
        return this.post;
    }

    public void delete(Integer id)
    {
        postService.delete(id);
    }

    @Transactional
    public void add(Integer id) throws IOException {
        if (title.isEmpty() || content.isEmpty()) {
            addMessage("Fill all fields");
            return;
        }
        postService.add(blog ,userService.getById(id), title, content, new Date());
        FacesContext.getCurrentInstance().getExternalContext().redirect("blogs.xhtml");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addPostForm(Blog blog) throws IOException {
        this.blog = blog;
        FacesContext.getCurrentInstance().getExternalContext().redirect("addPost.xhtml");
    }

    @Transactional
    public void delete(Post argPost)
    {
        postService.delete(argPost.getId());
    }
}
