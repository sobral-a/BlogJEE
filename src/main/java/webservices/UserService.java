package webservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import media.User;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by alexa on 17/06/2017.
 */

@WebService
@Path("/user")
@Produces("application/json; charset=UTF-8")
public class UserService
{
    @Inject
    private services.UserService userService;

    @Transactional
    @GET
    @Path("/{id}")
    public String getUser(@PathParam("id") final Integer id)
    {
        User user = userService.getById(id);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try
        {
            return ow.writeValueAsString(user);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    @Transactional
    @POST
    @Consumes("application/json; charset=UTF-8")
    public String createUser(final User user)
    {
        try
        {
            userService.add(user.getLogin(), user.getEmail());
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(true);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    @Transactional
    @DELETE
    @Consumes("application/json; charset=UTF-8")
    @Path("/{id}")
    public String deleteUser(@PathParam("id") final Integer id)
    {

        try
        {
            userService.delete(id);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(true);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    @Transactional
    @GET
    @Consumes("application/json; charset=UTF-8")
    public String getAll()
    {
        try
        {
            ArrayList<User> users = (ArrayList<User>) userService.list();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(users);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "error";
        }
    }


}
