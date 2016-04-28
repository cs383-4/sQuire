package squire.Networking;

import java.util.ArrayList;

/**
 * Describes an interface for a RequestHandler.
 * RequestHandles are called by the Router class to handle a request.
 *
 * The getRoutes that the RequestHandler can handle are specified by the getRoutes() function, this is used by the router
 * to know which getRoutes to pass to the RequestHandler. Each request handler has a prefix. This is added to the start
 * of every route name, acting like a namespace for each * RequestHandler.
 *
 * Ex: user/register ("user/" is the prefix, "register" is the route)
 *
 * Note: The prefix must be included in the route of the Request object.
 *
 * The handle function is called by the Router with a route, a request, and a response to modify before it is send back
 * to the client.
 */
interface RequestHandler {
    /**
     * @return a list of the getRoutes the RequestHandler can handle
     */
    String[] getRoutes();

    /**
     * @return the prefix to be added to all routes.
     */
    String getPrefix();

    /**
     * @param route the route the request is for. Contains the same value as req.getRoute()
     * @param req the request sent by the client
     * @param res the response that will be sent back to the client
     */
    void handle(String route, Request req, Response res);
}
