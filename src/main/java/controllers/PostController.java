package controllers;

import lombok.Getter;
import lombok.Setter;
import models.Blog;
import models.Post;
import services.BlogService;
import services.CommentService;
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
    private CommentService commentService;

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
        Post newPost= postService.getById(this.post.getId());
        return newPost;
    }

    public void delete(Integer id)
    {
        postService.delete(id);
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

    @Transactional
    public void addComment(Integer id, Post post) throws IOException {
        this.post = post;
        if (content.isEmpty()) {
            addMessage("Write a comment !");
            return;
        }
        commentService.add(userService.getById(id), post, content, new Date());
        Post newPost = postService.getById(post.getId());
        setPost(newPost);
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
