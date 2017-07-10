package webservices;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Created by alexa on 17/06/2017.
 */

@ApplicationPath("/api")
public class API extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        return getRestResourceClasses();
    }

    private Set<Class<?>> getRestResourceClasses()
    {
        Set<Class<?>> resources = new java.util.HashSet<Class<? >>();
        resources.add(UserService.class);
        return resources;
    }
}
