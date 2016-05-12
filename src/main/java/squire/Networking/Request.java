package squire.Networking;

/**
 * A request object is what's set to the server, and an object will be returned.
 *
 * The "route" parameter tells the server what to do with the request.
 *
 * Ex: to retrieve a user from the server:
 * Request req = new Request("getUser").set("token", userToken)
 * Response res = req.send()
 * String username = res.get("username")
 */

public class Request extends NetworkObject<Request> {
    @Override protected Request getThis() {return this;}
    public String route;

    /**
     * Creates a new Request with the specified query
     * The query is used by the server to figure out what to do with the request
     * @param route: the value to set as the query
     */
    public Request(String route) {
        this.route = route;
    }

    /**
     * @return the route of the request
     */
    String getRoute() {
        return route;
    }
    /**
     * Sends the request to the server
     * @return the response from the server
     */
    public Response send() {
        return Client.send(this);
    }
}
