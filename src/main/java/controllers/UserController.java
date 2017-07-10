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
        return userService.list();
    }

    @Transactional
    public void delete(Integer id)
    {
        userService.delete(id);
    }



}
