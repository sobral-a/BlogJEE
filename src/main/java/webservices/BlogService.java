package webservices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import models.Blog;
import models.Post;
import models.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 17/06/2017.
 */

@WebService
@Path("/blog")
@Produces("application/json; charset=UTF-8")
public class BlogService
{
    @Inject
    private services.BlogService blogService;

    @Transactional
    @GET
    public String getBlogs()
    {
        ArrayList<Blog> blogs = (ArrayList<Blog>) blogService.list();
        ArrayList<String> titles = new ArrayList<>();
        for (Blog b : blogs)
        {
            titles.add(b.getTitle());
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try
        {
            return ow.writeValueAsString(titles);
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
    @Path("/{id}")
    public String getPosts(@PathParam("id") final Integer id)
    {
        List<Post> posts = blogService.getById(id).getPosts();
        ArrayList<String> titles = new ArrayList<>();
        for (Post t : posts)
        {
            titles.add(t.getTitle());
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try
        {
            return ow.writeValueAsString(titles);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "error";
        }
    }





}
