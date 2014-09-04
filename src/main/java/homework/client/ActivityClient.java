package homework.client;

import homework.model.Activity;

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
public class ActivityClient {

    private final String URL = "http://localhost:8080/jersey/";
    private Client client;
    private WebTarget target;

    public ActivityClient() {
        client = ClientBuilder.newClient();
        target = client.target(URL);
    }

    //pojedinačna aktivnost
    public Activity get(String activityId) {
       Response response = target.path("activities/" + activityId)
               .request(MediaType.APPLICATION_JSON).get(Response.class);

        if(response.getStatus() != Response.Status.OK.getStatusCode())
           throw new RuntimeException(response.getStatus() + " error");

        return response.readEntity(Activity.class);
    }

    //lista svih aktivnosti
    public List<Activity> get() {
        List<Activity> activities = target.path("activities")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Activity>>() {});
        return activities;
    }


    //post klijent
    public Response create(Activity activity) {
        Response response = target.path("activities/activity")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(activity, MediaType.APPLICATION_JSON));
        if(response.getStatus() != Response.Status.OK.getStatusCode())
            throw new RuntimeException(response.getStatus() + " error");
        return response;
    }

    //put klijent
    public Activity update(Activity activity) {
        Response response = target.path("activities/activity" + activity.getId())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(activity, MediaType.APPLICATION_JSON));
        if(response.getStatus() != Response.Status.OK.getStatusCode())
            throw new RuntimeException(response.getStatus() + " error");
        return response.readEntity(Activity.class);
    }

    //delete klijent
    public void delete(Integer activityId) {
        Response response = target.path("activities/" + activityId)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        if(response.getStatus() != Response.Status.OK.getStatusCode())
            throw new RuntimeException(response.getStatus() + " error");
    }
}
