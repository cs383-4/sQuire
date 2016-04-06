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
}
