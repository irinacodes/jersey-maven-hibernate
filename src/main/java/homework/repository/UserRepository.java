package homework.repository;

import homework.model.Title;
import homework.model.User;
import homework.util.HibernateUtil;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ibranovic
 */
public class UserRepository {

    public List<User> findAll() {
        return HibernateUtil.findAll(User.class);
    }

    public User find(String userId) {
        return HibernateUtil.findByPrimaryKey(User.class, userId);
    }

    //svi nastavnici
    public static List<User> findInstructors() {
        List<String> titles = new ArrayList<String>();
        titles.add(Title.TEACHING_ASSISTANT);
        titles.add(Title.ASSISTANT_PROF);
        titles.add(Title.ASSOCIATED_PROF);
        titles.add(Title.FULL_PROF);
        return findByTitle(titles);
    }
    public static List<User> findDemonstrators() {
        List<String> titles = new ArrayList<String>();
        titles.add(Title.DEMONSTRATOR);
        return findByTitle(titles);
    }
    private static List<User> findByTitle(List<String> argsList) {
        Query query = HibernateUtil.createHqlQuery("from User user where user.title.name in (:argList)");
        query.setParameterList("argList", argsList);
        return query.list();
    }
    public static User findByUsername(String username) {
        Query query = HibernateUtil.createHqlQuery("from User user where user.username = :argUsername");
        query.setString("argUsername", username);
        return (User) query.uniqueResult();
    }
}
