package producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by alexa on 16/06/2017.
 */
public class EntityManagerProducer
{
    @Produces
    @ApplicationScoped
    public EntityManager createEm()
    {
        return Persistence.createEntityManagerFactory("factory"). createEntityManager();
    }

    public void disposeEm(@Disposes EntityManager em)
    {
        em.close();
    }

}
