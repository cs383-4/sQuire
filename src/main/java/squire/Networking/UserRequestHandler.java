package squire.Networking;

import squire.Users.Session;
import squire.Users.User;

import javax.persistence.NonUniqueResultException;

/**
 * The request handler dealing with all things Users
 */
@Route("user/")
class UserRequestHandler {
    @Route("register")
    static void register(Request req, Response res) {
        try {
            User u = new User((String) req.get("username"), (String) req.get("password"));
            u.save();
        } catch (NonUniqueResultException ex) {
            res.setFail();
        }
    }

    @Route("login")
    static void login(Request req, Response res) {
        User u = User.find.authenticate((String) req.get("username"), (String) req.get("password"));
        if (u == null) {
            res.setFail();
        }
        res.set("sessionID", Session.login(u).getToken());
    }
}

