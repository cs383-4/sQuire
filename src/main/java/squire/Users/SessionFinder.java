package squire.Users;
/**
 * A Session finder
 */

import com.avaje.ebean.Finder;
import squire.Users.query.QSession;

import java.util.UUID;

public class SessionFinder extends Finder<Long, User> {
    public SessionFinder() {
        super(User.class);
    }

    public QSession where() {
        return new QSession(db());
    }

    /**
     * Returns an active session if it exists with the given token
     *
     * @param token the token of the session to return
     * @return a Session if it exists, else null
     */
    public Session activeSession(String token) {
        Session s = where().token.equalTo(UUID.fromString(token)).findUnique();
        if (s == null) {
            //session doesn't exist
            return null;
        } else {
            if (s.isExpired()) {
                return null;
            } else {
                return s;
            }
        }
    }
}

