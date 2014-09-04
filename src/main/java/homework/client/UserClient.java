package homework.client;

import homework.model.User;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author ibranovic
 */
public class UserClient {
    private final String url = "http://localhost:8080/jersey/";
    private Client client;

    public UserClient() {
        client = ClientBuilder.newClient();
    }

    //pojedinačni korisnik
    public User get(String activityId) {
       WebTarget target = client.target(url);
       Response response = target.path("activities/" + activityId)
               .request(MediaType.APPLICATION_JSON).get(Response.class);
       if(response.getStatus() != Response.Status.OK.getStatusCode())
           throw new RuntimeException(response.getStatus() + " error");
       return response.readEntity(User.class);
    }

    //lista svih korisnika
    public List<User> get() {
        WebTarget target = client.target(url);
        List<User> response = target.path("activities")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<User>>() {});
        return response;
    }


    public Response create(User user) {
        WebTarget target = client.target(url);
        Response response = target.path("activities/activity")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        return response;
    }
}
