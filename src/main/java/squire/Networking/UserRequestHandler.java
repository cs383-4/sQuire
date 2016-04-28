package squire.Networking;

import squire.Users.Session;
import squire.Users.User;

import javax.persistence.NonUniqueResultException;

/**
 * The request handler dealing with all things Users
 */
class UserRequestHandler implements RequestHandler {
    @Override
    public String[] getRoutes() {
        return new String[]{"register", "login"};
    }

    @Override
    public String getPrefix() {
        return "user/";
    }

    @Override
    public void handle(String route, Request req, Response res) {
        User u;
        switch (route) {
            case "register":
                try {
                    u = new User((String) req.get("username"), (String) req.get("password"));
                    u.save();
                } catch (NonUniqueResultException ex) {
                    res.setFail();
                }

                break;
            case "login":
                u = User.find.authenticate((String) req.get("username"), (String) req.get("password"));
                if(u == null) {
                    res.setFail();
                }
                res.set("sessionID", Session.login(u).getToken());
                break;
        }
    }
}

