package squire.Networking;

import java.io.Serializable;
import java.util.HashMap;

/**
 * NetworkObject is the base class for Response and Request
 * It is what is sent between the Client and the Server.
 *
 * The getThis() method and the generic class definition is to allow chaining methods to work when subclassed.
 */
abstract class NetworkObject <T extends NetworkObject<T>> implements Serializable {
    protected HashMap<String, Object> data = new HashMap<>();

    protected abstract T getThis();

    /**
     * @return returns the raw HashMap containing all the data
     */
    public HashMap<String, Object> getData() {
        return data;
    }

    /**
     * Sets a key to the specified value
     * @param key: key to set
     * @param value: value to assign to the key
     * @return this; returns self allowing chaining commands
     */
    public NetworkObject set(String key, Object value) {
       data.put(key, value);
        return getThis();
    }

    /**
     * Returns the value assigned to a key
     * @param key: key to look up
     * @return the value assigned to the key
     */
    public Object get(String key) {
        return data.get(key);
    }
}
