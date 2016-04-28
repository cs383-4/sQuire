package squire.Networking;

import java.util.HashMap;

/**
 * Used by the server to delegate requests to a particular class
 * All the routes need to be defined in the constructor
 */

class Router {
    private HashMap<String, RequestHandler> routes = new HashMap<>();

    Router() {
        //define all the routes here
        addRoutes(new UserRequestHandler());
    }

    /**
     * Routes a request to the correct function, and then returns the response
     * @param req the request (from the client) to route
     * @return returns the response to send back to the client
     */
    Response route(Request req) {
        Response res = new Response();
        routes.get(req.getRoute()).handle(req.getRoute(), req, res);
        return res;
    }

    /**
     * Add all the routes a class can handle to the router
     * @param handler an instance of the class to add the routes of
     */
    private void addRoutes(RequestHandler handler) {
        for(String query : handler.routes()) {
            routes.put(query, handler);
        }
    }
}
