package squire.Users;
/**
 * A user finder.
 */

import com.avaje.ebean.Finder;
import squire.Users.query.QUser;

public class UserFinder extends Finder<Long, User> {
    public UserFinder() {
        super(User.class);
    }

    public QUser where() {
        return new QUser(db());
    }

    /**
     * Authenticate a user with the given username and password
     * Note: this doesn't create a new session.
     *
     * @param username the username of the user to authenticate
     * @param password the password to try
     * @return the user, if the username and password are correct, else null
     */
    public User authenticate(String username, String password) {
        User u = where().username.equalTo(username).findUnique();
        if (u == null) {
            //user doesn't exist
            return null;
        } else {
            if (u.checkPassword(password)) {
                //password correct!
                return u;
            } else {
                //incorrect password
                return null;
            }
        }
    }
}
