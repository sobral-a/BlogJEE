package controllers;

import interceptor.Transaction;
import lombok.Getter;
import lombok.Setter;
import models.Blog;
import models.Comment;
import models.Post;
import services.BlogService;
import services.CommentService;
import services.PostService;
import services.UserService;

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
@Named("commentController")
public class CommentController implements Serializable
{
    @Getter @Setter
    private String content;

    @Inject
    private CommentService commentService;
    @Inject
    private UserService userService;

    @Transactional
    public void delete(Comment comment)
    {
        commentService.delete(comment.getId());
    }
}
