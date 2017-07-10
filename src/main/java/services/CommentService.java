package services;

import dao.GenericAccess;
import models.Blog;
import models.Comment;
import models.Post;
import models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 16/06/2017.
 */

@ApplicationScoped
public class CommentService
{
    @Inject
    private GenericAccess commentAccess;

    public void add(User user, Post post, String content, Date date)
    {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);
        comment.setDate(date);
        comment.setIsDeleted(false);
        commentAccess.add(comment);
}

    public void delete(Integer id)
    {
        Comment comment  = commentAccess.getById(Comment.class, id);
        comment.setIsDeleted(true);
        commentAccess.Update(comment);
    }

    public void update(Integer id, User user, Post post, String content, Date date)
    {
        Comment comment = commentAccess.getById(Comment.class, id);
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);
        comment.setDate(date);
        commentAccess.Update(comment);
    }

    public Comment getById(Integer id)
    {
        return commentAccess.getById(Comment.class, id);
    }

    public List<Comment> list()
    {
        return commentAccess.list(new Comment());
    }
}