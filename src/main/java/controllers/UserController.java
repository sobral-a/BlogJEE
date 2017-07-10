package controllers;

import lombok.Getter;
import lombok.Setter;
import models.User;
import services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 17/06/2017.
 */

@SessionScoped
@Named("userController")
public class UserController implements Serializable
{
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String password;

    @Inject
    private UserService userService;


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

    @Transactional
    public void addUser(String role) {
        if (name.isEmpty() || firstName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            addMessage("Fill all the fields!");
            return;
        }
        try {
            userService.add(name, firstName, email, password, role);
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("/webApp/connection.xhtml");
        } catch(Exception e) {
            addMessage("Something goes wrong...");
        }
    }

    public void connect() {
        if (email.isEmpty() || password.isEmpty()) {
            addMessage("Fill all the fields!");
            return;
        }
        try {
            if (!userService.checkConnection(email, password)) {
                addMessage("Wrong email or password");
                return;
            }
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("/webApp/blogs.xhtml");
        } catch(Exception e) {
            addMessage("Something goes wrong...");
        }
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Transactional
    public void delete(Integer id)
    {
        userService.delete(id);
    }
}
