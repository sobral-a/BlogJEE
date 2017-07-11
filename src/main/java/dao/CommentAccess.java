package dao;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by alexa on 11/07/2017.
 */
@SessionScoped
public class CommentAccess extends GenericAccess
{

    @PersistenceContext(unitName = "context")
    private EntityManager em;

    @Override
    protected EntityManager getEm()
    {
        return em;
    }
}