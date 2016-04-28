package squire.Networking;

import java.util.ArrayList;

/**
 * Describes an interface for a RequestHandler.
 * RequestHandles are called by the Router class to handle a request.
 *
 * The routes that the RequestHandler can handle are specified by the routes() function, this is used by the router
 * to know which routes to pass to the RequestHandler
 *
 * The handle function is called by the Router with a route, a request, and a response to modify before it is send back
 * to the client.
 */
interface RequestHandler {
    /**
     * @return a list of the routes the RequestHandler can handle
     */
    String[] routes();

    /**
     * @param route the route the request is for. Contains the same value as req.getRoute()
     * @param req the request sent by the client
     * @param res the response that will be sent back to the client
     */
    void handle(String route, Request req, Response res);
}
