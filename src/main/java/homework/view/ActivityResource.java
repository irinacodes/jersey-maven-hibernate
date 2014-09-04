package homework.view;

import homework.repository.ActivityRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;
import homework.model.Activity;
import homework.util.Validate;

@Path("activities")
//http://localhost:8080/jersey/activities

/**
 * @author ibranovic
 */
public class ActivityResource {

    private ActivityRepository activityRepository = new ActivityRepository();

    public ActivityResource() {}

    public ActivityResource(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{activityId}")
    public Response find(@PathParam("activityId") String activityId) {

        if(Validate.exists(activityId))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Activity activity = activityRepository.find(activityId);

        if(activity == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok().entity(activity).build();
    }

    //post iz običnog HTML obrasca
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{activity}")
    public Activity createActivityParams(MultivaluedMap<String, String> formParams) {
        Activity activity = new Activity();
        activity.setName(formParams.getFirst("name"));
        activity.setCoefficient(Double.valueOf(formParams.getFirst("coefficient")));
        activityRepository.create(activity);
        return activity;
    }

    //binding za objekat
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{activity}")
    public Activity createActivity(Activity activity) {
       return activityRepository.create(activity);
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{activityId}")
    public Activity updateActivity(Activity activity) {
        return activityRepository.update(activity);
    }

    @DELETE
    @Path("{activityId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("activityId") String activityId) {
        activityRepository.delete(activityId);
        return Response.ok().build();
    }

}
