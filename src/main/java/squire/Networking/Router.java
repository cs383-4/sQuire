package squire.Networking;

import java.util.HashMap;

/**
 * Used by the server to delegate requests to a particular class
 * All the getRoutes need to be defined in the constructor
 */

class Router {
    private HashMap<String, RequestHandler> routes = new HashMap<>();

    Router() {
        //define all the getRoutes here
        addRoutes(new UserRequestHandler());
    }

    /**
     * Routes a request to the correct function, and then returns the response
     * @param req the request (from the client) to route
     * @return returns the response to send back to the client
     */
    Response route(Request req) {
        Response res = new Response();
        //get the handler
        RequestHandler handler = routes.get(req.getRoute());
        //strip the prefix from the route
        String route = req.getRoute().replaceFirst(handler.getPrefix(), "");
        //call the handler
        handler.handle(route, req, res);
        return res;
    }

    /**
     * Add all the getRoutes a class can handle to the router
     * @param handler an instance of the class to add the getRoutes of
     */
    private void addRoutes(RequestHandler handler) {
        for(String query : handler.getRoutes()) {
            routes.put(handler.getPrefix() + query, handler);
        }
    }
}
