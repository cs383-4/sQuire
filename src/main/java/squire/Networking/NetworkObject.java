package squire.Networking;

import java.util.HashMap;

/**
 * NetworkObject is the base class for Response and Request
 * It is what is sent between the Client and the Server.
 *
 * The getThis() method and the generic class definition is to allow chaining methods to work when subclassed.
 */
public abstract class NetworkObject <T extends NetworkObject<T>> {
    protected HashMap<String, Object> data = new HashMap<>();

    protected abstract T getThis();

    public HashMap<String, Object> getData() {
        return data;
    }

    public NetworkObject set(String key, Object value) {
       data.put(key, value);
        return getThis();
    }

    public Object get(String key) {
        return data.get(key);
    }
}
