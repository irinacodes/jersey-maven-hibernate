package homework.client;

import homework.model.Activity;
import homework.model.ActivitySearch;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ibranovic
 */
public class ActivityClientTest {

    @Test
    public void testPut() {
        Activity activity = new Activity();
        activity.setId(1);
        activity.setName("dežurstvo bez ocenjivanja");
        activity.setCoefficient(1.0);
        ActivityClient client = new ActivityClient();
        activity = client.update(activity);
        Assert.assertNotNull(activity);
    }

    @Test
    public void testCreate() {
        ActivityClient client = new ActivityClient();
        Activity activity = new Activity();
        activity.setName("restful proba");
        activity.setCoefficient(2.0);
        client.create(activity);
        Assert.assertNotNull(activity);
    }

    @Test
    public void testGet() {
      ActivityClient client = new ActivityClient();
      Activity activity = client.get("1");
      Assert.assertNotNull(activity);
    }

    @Test
    public void testGetList() {
        ActivityClient client = new ActivityClient();
        List<Activity> activities = client.get();
        Assert.assertNotNull(activities);
    }

    @Test(expected=RuntimeException.class)
    public void testGetWithBadRequest() {
        ActivityClient client = new ActivityClient();
        client.get("123");
    }

    @Test(expected=RuntimeException.class)
    public void testDelete() {
        ActivityClient client = new ActivityClient();
        client.delete(12);
    }

    @Test
    public void testSearch() {
        ActivitySearchClient client = new ActivitySearchClient();
        String param = "name";
        List<String> searchValues = new ArrayList<String>();
        searchValues.add("dežurstvo sa ocenjivanjem");
        searchValues.add("spavanje na vežbama");
        List<Activity> activities = client.search(param, searchValues);
        Assert.assertNotNull(activities);
    }

    @Test
    public void testUnsuccessfullSearch() {
        ActivitySearchClient client = new ActivitySearchClient();
        String param = "name";
        List<String> searchValues = new ArrayList<String>();
        searchValues.add("dežurstvo");
        searchValues.add("spavanje");
        List<Activity> activities = client.search(param, searchValues);
        Assert.assertTrue(activities.isEmpty());
    }

    @Test
    public void testSearchObject() {
        ActivitySearchClient client = new ActivitySearchClient();
        List<String> searchValues = new ArrayList<String>();
        searchValues.add("dežurstvo na vežbama");
        searchValues.add("spavanje na vežbama");
        ActivitySearch activitySearch = new ActivitySearch();
        activitySearch.setNames(searchValues);
        activitySearch.setCoefficientFrom(1.0);
        activitySearch.setCoefficientTo(1.2);
        List<Activity> activities = client.search(activitySearch);
        Assert.assertNotNull(activities);

    }

}
