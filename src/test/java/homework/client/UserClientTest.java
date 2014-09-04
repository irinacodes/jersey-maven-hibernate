package homework.client;

import homework.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author ibranovic
 */
public class UserClientTest {

    @Test
    public void testCreate() {
        UserClient client = new UserClient();
        User user = new User();
        user.setName("restful user");
        user.setActive(true);
        user.setAdmin(false);
        user = client.create(user).readEntity(User.class);
        Assert.assertNotNull(user);
    }

    @Test
    public void testGet() {
      UserClient client = new UserClient();
      User user = client.get("11");
      Assert.assertNotNull(user);
    }

    @Test
    public void testGetList() {
        UserClient client = new UserClient();
        List<User> users = client.get();
        Assert.assertNotNull(users);
    }

    @Test(expected=RuntimeException.class)
    public void testGetWithBadRequest() {
        UserClient client = new UserClient();
        client.get("123");
    }

}
