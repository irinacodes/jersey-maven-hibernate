package homework.repository;

import homework.model.Activity;
import homework.model.ActivitySearch;
import homework.util.HibernateUtil;
import org.hibernate.Query;

import java.util.List;

/**
 * @author ibranovic
 */
public class ActivityRepository {

    public List<Activity> findAll() {
        return HibernateUtil.findAll(Activity.class);
    }

    public Activity find(String id) {
        return HibernateUtil.findByPrimaryKey(Activity.class, id);
    }

    public Activity create(Activity activity) {
       HibernateUtil.saveOrUpdate(activity);
       return activity;
    }

    public Activity update(Activity activity) {
        return create(activity);
    }

    public void delete(String activityId) {
        Activity activity = HibernateUtil.findByPrimaryKey(Activity.class, Integer.valueOf(activityId));
        HibernateUtil.delete(activity);
    }

    public List<Activity> findByName(List<String> names) {
        String hql = " from Activity a where a.name in (:namesList)";
        Query query = HibernateUtil.createHqlQuery(hql);
        query.setParameterList("namesList", names);
        return (List<Activity>) query.list();

    }

    public List<Activity> findByConstraints(ActivitySearch activitySearch) {
        String hql = " from Activity a where a.name in (:namesList) and a.coefficient >= :coefFrom and a.coefficient <= :coefTo";
        Query query = HibernateUtil.createHqlQuery(hql);
        query.setParameterList("namesList", activitySearch.getNames());
        query.setParameter("coefFrom", activitySearch.getCoefficientFrom());
        query.setParameter("coefTo", activitySearch.getCoefficientTo());
        return (List<Activity>) query.list();
    }
}
