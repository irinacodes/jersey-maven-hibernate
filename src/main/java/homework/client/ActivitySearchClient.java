package homework.client;

import homework.model.Activity;
import homework.model.ActivitySearch;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * @author ibranovic
 */
public class ActivitySearchClient {
    private final String URL = "http://localhost:8080/jersey/";
    private Client client;
    private WebTarget target;

    public ActivitySearchClient() {
        client = ClientBuilder.newClient();
    }

    //parsiranje parametara za pretragu iz zahteva
    public List<Activity> search(String param, List<String> searchValues) {
        //kreira se uri da bi se zakačili parametri
        constructTarget("search/activities");
        //http://localhost:8080/jersey/search/activities/name=dezurstvo&name=spavanje
        List<Activity> response = target.request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Activity>>(){});
        return response;
    }

    //korišćenje objekta umesto parametara za pretragu
    public List<Activity> search(ActivitySearch activitySearch) {
        constructTarget("search/activities");
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(activitySearch, MediaType.APPLICATION_JSON));
        if(response.getStatus() != Response.Status.OK.getStatusCode())
            throw new RuntimeException(response.getStatus() + " error");
        return response.readEntity(new GenericType<List<Activity>>() {});
    }

    private void constructTarget(String pathArg) {
        URI uri = UriBuilder.fromUri(URL).path(pathArg).build();
        target = client.target(uri);
    }
}
