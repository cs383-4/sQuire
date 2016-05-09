package squire.Users;
/**
 * A Project finder
 */

import com.avaje.ebean.Finder;
import squire.Users.User;
import squire.Users.query.QSession;

import java.util.UUID;

public class ProjectFinder extends Finder<Long, User> {
    public ProjectFinder() {
        super(User.class);
    }



}

