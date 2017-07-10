package main;

import models.User;
import services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Kevin on 10/06/2017.
 */

@ApplicationScoped
public class Main {

    @PersistenceContext(unitName = "context")
    private EntityManager em;

    public static void main(String[] args)
    {
        /*Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        UserService userService = container.select(UserService.class).get();

        userService.add("sobral_r", "sobral_r@epita.fr");
        List<User> users = userService.list();
        User user = userService.getById(2);
        System.out.println(user.toString());

        container.shutdown();*/

    }
}
