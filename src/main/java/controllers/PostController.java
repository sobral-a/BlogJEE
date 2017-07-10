package controllers;

import models.Blog;
import models.Post;
import services.BlogService;
import services.PostService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by alexa on 10/07/2017.
 */

@SessionScoped
@Named("postController")
public class PostController implements Serializable
{
    @Inject
    private PostService postService;

    private Post post;

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

}
