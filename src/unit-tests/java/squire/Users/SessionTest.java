package squire.Users;

import org.junit.Test;

import java.util.Date;
import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by brandon on 4/25/16.
 */
public class SessionTest {
    @Test
    public void isExpired() throws Exception {
        User u = new User("Jim", "jim@email.com", "password");
        Session s = Session.login(u);
        s.setExpires(new Timestamp(0));
        s.save();
        assertTrue("failure - session not recognized as expired", s.isExpired());
        s.setExpires(new Timestamp(new Date().getTime() + 100000));
        assertFalse("failure - session incorrectly recognized as expired", s.isExpired());
    }
    @Test
    public void logout() throws Exception {
        User u = new User("Jim2", "jim2@email.com", "password");
        Session s = Session.login(u);
        Long sessionID = s.getId();
        s.logout();
        assertNull("failure - session not deleted", Session.find.where().id.equalTo(sessionID).findUnique());
    }

    @Test
    public void activeSession() throws Exception {
        User u = new User("Jim3", "jim3@email.com", "password");
        Session s = Session.login(u);
        String token = s.getToken();

        assertEquals("failure - wrong session returned", s, Session.find.activeSession(token));
        assertNull("failure - session returned for non-existent token", Session.find.activeSession(UUID.randomUUID().toString()));
        s.setExpires(new Timestamp(0));
        s.save();
        assertNull("failure - expired session returned", Session.find.activeSession(token));
    }
}