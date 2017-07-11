package dao;

import models.Blog;
import models.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khalis on 10/07/2017.
 */
@SessionScoped
public class BlogAccess extends GenericAccess
{
    @PersistenceContext(unitName = "context")
    private EntityManager em;

    public List<Blog> getList(Boolean isDeleted)
    {
        Query query = em.createQuery("Select blog from Blog blog where blog.isDeleted=:isDeleted");
        List<Object> list = query.setParameter("isDeleted", isDeleted).getResultList();
        ArrayList<Blog> blogs = new ArrayList<>();
        for (Object b : list)
        {
            blogs.add((Blog)b);
        }
        return blogs;
    }

    @Override
    protected EntityManager getEm()
    {
        return em;
    }
}
