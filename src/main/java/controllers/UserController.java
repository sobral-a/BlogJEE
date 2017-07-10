package controllers;

import interceptor.Transaction;
import media.User;
import services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
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

    @Transactional
    public void add(String email, String login)
    {
        userService.add(login, email);
    }

}
