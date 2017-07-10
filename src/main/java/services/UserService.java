package services;

import dao.GenericAccess;
import models.Comment;
import models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by alexa on 16/06/2017.
 */

@ApplicationScoped
public class UserService
{
    @Inject
    private GenericAccess userAccess;

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

    public void delete(Integer id)
    {
        User user = userAccess.getById(User.class, id);
        user.setIsDeleted(true);
        userAccess.Update(id);
    }

    public void update(Integer id, String name, String firstName, String email, String password, String role)
    {
        User user  = userAccess.getById(User.class, id);
        user.setEmail(email);
        user.setName(name);
        user.setFirstName(firstName);
        user.setPassword(password);
        user.setRole(role);
        userAccess.Update(user);
    }

    public User getById(Integer id)
    {
        return userAccess.getById(User.class, id);
    }

    public List<User> list()
    {
        return userAccess.list(new User());
    }
}
