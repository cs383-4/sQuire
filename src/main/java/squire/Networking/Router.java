package squire.Networking;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Used by the server to delegate requests to a particular function.
 *
 * All the classes which handle routes need to be added in the constructor.
 * Adding a class will add all the static functions inside of a class with a @Route annotation.
 *
 * If a class has a @Route annotation, the name of the class route will be prefixed to all the names of the function
 * routes
 */

class Router {
    private HashMap<String, Method> routes = new HashMap<>();

    Router() {
        //define all the routes here
        addRoutes(UserRequestHandler.class);
        addRoutes(ProjectRequestHandler.class);
    }

    /**
     * Routes a request to the correct function, and then returns the response
     * @param req the request (from the client) to route
     * @return returns the response to send back to the client
     */
    Response route(Request req) {
        Response res = new Response();
        //get the handler
        Method routeHandler = routes.get(req.getRoute());

        //call the handler
        try {
            //everything is a static method, so no need to pass an instance of the class
            routeHandler.invoke(null, req, res);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
            System.out.println("Error calling method " + routeHandler.getName());
        }
        return res;
    }

    /**
     * Add all the getRoutes a class can handle to the router
     * @param handler an instance of the class to add the getRoutes of
     */
    private void addRoutes(Class<?> handler) {
        String routePrefix = "";
        //set prefix if it exists
        for(Annotation a: handler.getDeclaredAnnotations()) {
            if(a instanceof Route) {
                routePrefix += ((Route) a).value();
            }
        }

        //add all the defined routes
        for(Method m: handler.getDeclaredMethods()) {
            for(Annotation a: m.getDeclaredAnnotations()) {
                if(a instanceof Route) {
                    String route = ((Route) a).value();
                    routes.put(routePrefix + route, m);
                }
            }
        }
    }
}
