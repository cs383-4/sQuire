package squire.Networking;

import java.util.HashMap;

/**
 * A request object is what's set to the server, and an object will be returned.
 *
 * The "query" parameter tells the server what to do with the request.
 *
 * Ex: to retrieve a user from the server:
 * Request req = new Request("getUser").set("token", userToken)
 * Response res = req.send()
 * String username = res.get("username")
 */
public class Request extends NetworkObject<Request> {
    @Override protected Request getThis() {return this;}

    public Request(String query) {
        data.put("query", query);
    }

    public Response send() {
        return Client.send(this);
    }
}
