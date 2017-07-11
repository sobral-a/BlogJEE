package controllers;

import lombok.Getter;
import lombok.Setter;
import models.Blog;
import models.Comment;
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
    /*** GETTER SETTER ***/
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String content;

    /*** SERVICES ***/
    @Inject
    private PostService postService;
    @Inject
    private UserService userService;
    @Inject
    private CommentService commentService;

    private Post post;
    private Blog blog;

    //Set post
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

    //Get post
    public Post getPost()
    {
        Post newPost = postService.getById(this.post.getId());
        return newPost;
    }

    //Delete by id
    public void delete(Integer id)
    {
        postService.delete(id);
    }

    //Go to the add post form
    public void addPostForm(Blog blog) throws IOException {
        this.blog = blog;
        FacesContext.getCurrentInstance().getExternalContext().redirect("addPost.xhtml");
    }

    //Delete post by id
    @Transactional
    public void delete(Post argPost)
    {
        postService.delete(argPost.getId());
    }

    //Add comment in bdd
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

    //Error message
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Transactional
    public void delete(Comment comment) throws IOException
    {
        commentService.delete(comment.getId());
        Post newPost = postService.getById(comment.getPost().getId());
        this.post = newPost;
        FacesContext.getCurrentInstance().getExternalContext().redirect("post.xhtml");
    }
}
