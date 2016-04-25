package squire.Users;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by brandon on 4/25/16.
 */
public class UserTest {
    static User u;
    @BeforeClass
    public static void setUp() throws Exception {
        u = new User("username", "password");
        u.save();
    }

    @Test
    public void getSetUsername() throws Exception {
        String username = "User1";
        u.setUsername(username);
        u.save();
        assertEquals(u.getUsername(), username);
    }

    @Test
    public void setCheckPassword() throws Exception {
        String password = "password1";
        u.setPassword(password);
        assertTrue("failure - set then check password failed", u.checkPassword(password));
        assertFalse("failure - invalid password accepted", u.checkPassword(password + "Wrong"));
    }

    @Test
    public void authenticate() throws Exception {
        String username = "u";
        String password = "p";
        u = new User(username, password);
        assertEquals("failure - user not authenticated", u, User.find.authenticate(username, password));
        assertNull("failure - bad password authenticated", User.find.authenticate(username, password + "1"));
        assertNull("failure - not existant user authenticated", User.find.authenticate(username + "1", password));
    }

    @Test
    public void userQuery() throws Exception {
        String username = "Bill";
        String password = "password";
        u = new User(username, password);
        List<User> users = User.find.where().username.equalTo(username).findList();
        assertTrue("failure - querry didn't return anything", users.size() > 0);
    }
}