package dao;

import interceptor.Transaction;
import media.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alexa on 16/06/2017.
 */

@ApplicationScoped
public class GenericAccess
{

    @PersistenceContext(unitName = "context")
    private EntityManager em;

    public <T> void add(T object)
    {
        em.persist(object);
    }

    public <T> void delete(Class<T> type,Integer id)
    {

        T obj = em.find(type, id);
        em.remove(obj);
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
