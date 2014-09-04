package homework.view;

import homework.model.Course;
import homework.model.Title;
import homework.model.User;
import homework.repository.UserRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

/**
 * @author ibranovic
 */

@Path("users")
public class UserResource {
    private UserRepository userRepository = new UserRepository();

    public UserResource() {}

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //nađi sve korisnike
    //http://localhost:8080/jersey/users
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //nađi korisnika po id
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{userId}")
    public User find(@PathParam("userId") String id) {
        return userRepository.find(id);
    }

    //nađi zvanje za odgovarajućeg korisnika
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{userId}/title")
    public Title getUserTitle(@PathParam("userId") String id) {
        return userRepository.find(id).getTitle();
    }

    //nađi predmete za odgovarajućeg korisnika
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{userId}/courses")
    public Set<Course> getUserCourses(@PathParam("userId") String id) {
        return userRepository.find(id).getCourses();
    }
}
