package dao;

import models.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by khalis on 10/07/2017.
 */

@SessionScoped
public class UserAccess extends GenericAccess {
    @PersistenceContext(unitName = "context")
    private EntityManager em;

    public Boolean checkConnection(String email, String password) {
        Query query = em.createQuery("Select user from User user where user.email=:email");
        Object user = query.setParameter("email", email).getSingleResult();
        if (((User) user).getPassword().equals(password)) {
            Object session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            ((HttpSession) session).setAttribute("userId", ((User) user).getId());
            ((HttpSession) session).setAttribute("userRole", ((User) user).getRole());
            return true;
        }
        return false;
    }

    @Override
    protected EntityManager getEm()
    {
        return em;
    }
}
