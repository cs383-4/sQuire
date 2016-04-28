package squire.Networking;

/**
 * A response object is returned by the server as the result of a Request.send()
 *
 * As it is from the same superclass, it has the same API for get/set.
 *
 * Contains setFail and getSuccess. By default, the state is success.
 */
public class Response extends NetworkObject {
    @Override protected Response getThis() {return this;}

    private boolean succeed = true;

    /**
     * Sets the fail state
     */
    public void setFail() {
        succeed = false;
    }

    /**
     * @return either true for success, or false if there was an error processing the response
     */
    public boolean getSuccess() {
        return succeed;
    }

}
