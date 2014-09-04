package homework.view;

import homework.model.Activity;
import homework.model.ActivitySearch;
import homework.repository.ActivityRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author ibranovic
 */
@Path("search/activities")
public class ActivitySearchResource {

    private ActivityRepository activityRepository = new ActivityRepository();

    public ActivitySearchResource() {}

    public ActivitySearchResource(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response searchForActivities(@QueryParam(value = "name") List<String> names) {
         List<Activity> activities = activityRepository.findByName(names);
         if(activities == null)
             return Response.status(Response.Status.NOT_FOUND).build();
         return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response searchForActivities(ActivitySearch activitySearch) {
        List<Activity> activities = activityRepository.findByConstraints(activitySearch);
        if(activities == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
    }
}
