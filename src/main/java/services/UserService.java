package services;

import dao.GenericAccess;
import media.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alexa on 16/06/2017.
 */

@ApplicationScoped
public class UserService
{
    @Inject
    private GenericAccess userAccess;

    public void add(String login, String email)
    {
        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        userAccess.add(user);
    }

    public void delete(Integer id)
    {
        userAccess.delete(User.class, id);
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
