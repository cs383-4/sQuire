package squire.Users;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
}