package squire.Networking;

import squire.Main;
import squire.Users.Session;
import squire.Users.User;

import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.sql.SQLException;

/**
 * The request handler dealing with all things Users
 */
@Route("user/")
class UserRequestHandler
{
    @Route("register")
    static void register(Request req, Response res)
    {
        // If there is no user with this username and email.
        if (User.find.where().username.equalTo((String)req.get("username")).and().email.equalTo((String)req.get("email")).findUnique() == null)
        {
            User u = new User((String)req.get("username"), (String)req.get("email"), (String)req.get("password"));
            u.save();
        }
        else
        {
            res.setFail();
        }
    }

    @Route("login")
    static void login(Request req, Response res)
    {
        User u = User.find.authenticate((String)req.get("username"), (String) req.get("password"));
        if (u == null)
        {
            res.setFail();
        }
        res.set("sessionID", Session.login(u).getToken());
        res.set("userName", u.getUsername());
        res.set("userID", u.getId());
    }

    @Route("getUsernameFromSessionId")
    static void getUsernameFromSessionId(Request req, Response res)
    {
        User u = Session.find.activeSession((String) req.get("sessionID")).getUser();
        if (u != null)
        {
            res.set("username", u.getUsername());
        }
    }

    @Route("ChangePassword")
    static void ChangePassword(Request req, Response res)
    {
        User u = Session.find.activeSession((String) req.get("sessionID")).getUser();
        u.setPassword((String) req.get("password"));
        u.save();
    }

}