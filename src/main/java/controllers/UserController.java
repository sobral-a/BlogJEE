package controllers;

import models.User;
import services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alexa on 17/06/2017.
 */

@ApplicationScoped
@Named("userController")
public class UserController
{
    @Inject
    private UserService userService;

    public String getName()
    {
        return "UserController";
    }

    public List<User> getUserList()
    {
        List<User> users = userService.list();
        return users;
    }

    public User getUser(Integer id)
    {
        User user = userService.getById(id);
        return user;
    }

    public void delete(Integer id)
    {
        userService.delete(id);
    }



}
