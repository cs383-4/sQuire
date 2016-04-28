package squire.Networking;

import java.util.HashMap;

/**
 * A response object is returned by the server as the result of a Request.send()
 *
 * As it is from the same superclass, it has the same API for get/set
 */
public class Response extends NetworkObject {
    @Override protected Response getThis() {return this;}

}
