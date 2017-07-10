package dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 16/06/2017.
 */

@SessionScoped
@Any
public class GenericAccess implements Serializable
{

    @PersistenceContext(unitName = "context")
    private EntityManager em;

    public <T> void add(T object)
    {
        em.persist(object);
    }

    public <T> void delete(Class<T> type, Integer id)
    {
        T obj = em.find(type, id);
        em.remove(obj);
    }

    public <T> void update(Object object)
    {
        em.merge(object);
    }

    public <T> List<T> list(T type)
    {
        List<T> list = em.createQuery("Select a from " + type.getClass().getSimpleName()  + " a")
                .getResultList();
        return list;
    }

    public <T> T getById(Class<T> type, Integer id)
    {
        T obj = em.find(type, id);
        return obj;
    }
}
