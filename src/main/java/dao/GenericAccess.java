package dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 16/06/2017.
 */

public abstract class GenericAccess implements Serializable
{
    protected abstract EntityManager getEm();

    public <T> void add(T object)
    {
        getEm().persist(object);
    }

    public <T> void delete(Class<T> type, Integer id)
    {
        T obj = getEm().find(type, id);
        getEm().remove(obj);
    }

    public <T> void update(Object object)
    {
        getEm().merge(object);
    }

    public <T> List<T> list(T type)
    {
        List<T> list = getEm().createQuery("Select a from " + type.getClass().getSimpleName()  + " a")
                .getResultList();
        return list;
    }

    public <T> T getById(Class<T> type, Integer id)
    {
        T obj = getEm().find(type, id);
        return obj;
    }
}
