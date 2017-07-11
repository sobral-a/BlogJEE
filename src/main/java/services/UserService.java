package services;

import dao.GenericAccess;
import dao.UserAccess;
import models.Comment;
import models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 16/06/2017.
 */

@SessionScoped
public class UserService implements Serializable
{
    @Inject
    private UserAccess userAccess;

    //Add user in bdd
    public void add(String name, String firstName, String email, String password, String role)
    {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setFirstName(firstName);
        user.setPassword(password);
        user.setRole(role);
        user.setIsDeleted(false);
        userAccess.add(user);
    }

    //Delete user by id
    public void delete(Integer id)
    {
        User user = userAccess.getById(User.class, id);
        user.setIsDeleted(true);
        userAccess.update(id);
    }

    //Update user
    public void update(Integer id, String name, String firstName, String email, String password, String role)
    {
        User user  = userAccess.getById(User.class, id);
        user.setEmail(email);
        user.setName(name);
        user.setFirstName(firstName);
        user.setPassword(password);
        user.setRole(role);
        userAccess.update(user);
    }

    //Get user by id
    public User getById(Integer id)
    {
        return userAccess.getById(User.class, id);
    }

    //List all users
    public List<User> list()
    {
        return userAccess.list(new User());
    }

    //Check connection
    public Boolean checkConnection(String email, String password) {
        return userAccess.checkConnection(email, password);
    }
}
