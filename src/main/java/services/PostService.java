package services;

import com.sun.scenario.effect.impl.prism.ps.PPSOneSamplerPeer;
import dao.GenericAccess;
import models.*;
import models.Post;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 16/06/2017.
 */

@SessionScoped
public class PostService implements Serializable
{
    @Inject
    private GenericAccess postAccess;

    public void add(Blog blog, User user, String title, String content, Date date, Boolean isDeleted)
    {
        Post post = new Post();
        post.setUser(user);
        post.setBlog(blog);
        post.setTitle(title);
        post.setContent(content);
        post.setCreationDate(date);
        post.setIsDeleted(false);
        postAccess.add(post);
}

    public void delete(Integer id)
    {
        Post post  = postAccess.getById(Post.class, id);
        post.setIsDeleted(true);
        postAccess.update(post);
    }

    public void update(Integer id, Blog blog, User user, String title, String content)
    {
        Post post = postAccess.getById(Post.class, id);
        post.setUser(user);
        post.setBlog(blog);
        post.setTitle(title);
        post.setContent(content);
        postAccess.update(post);
    }


    public Post getById(Integer id)
    {
        return postAccess.getById(Post.class, id);
    }

    public List<Post> list()
    {
        return postAccess.list(new Post());
    }
}
