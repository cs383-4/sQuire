package squire.Networking;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An interface to specify a route to either a function or a class.
 * This is used by the router to know which routes to send to a particular function.
 *
 * A route applied to a class will be used as a prefix to all the other routes in a class
 */
@Retention(RetentionPolicy.RUNTIME)
@interface Route {
    String value() default "";
}
