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
    /*** GETTER SETTER ***/
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String password;

    /*** SERVICE ***/
    @Inject
    private UserService userService;


    //Get all users
    public List<User> getUserList()
    {
        List<User> users = userService.list();
        return users;
    }

    //Get user by id
    public User getUser(Integer id)
    {
        User user = userService.getById(id);
        return user;
    }

    //Add user in bdd
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

    //Connect the user
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

    //Error message
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //Delete by id
    @Transactional
    public void delete(Integer id)
    {
        userService.delete(id);
    }

    //Go to the connection page
    public void connection() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("connection.xhtml");
    }

    //Go to the register page
    public void register() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("register.xhtml");
    }

    //Logout
    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("connection.xhtml");
    }

    //Go to blog page
    public void blog() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("blogs.xhtml");
    }
}
