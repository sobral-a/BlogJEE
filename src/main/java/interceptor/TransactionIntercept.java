package interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by alexa on 16/06/2017.
 */
@Interceptor
@Transaction
public class TransactionIntercept
{
    @PersistenceContext(unitName = "context")
    private EntityManager em;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception
    {
        em.getTransaction().begin();
        final Object result = context.proceed();
        em.getTransaction().commit();
        return result;
    }
}
