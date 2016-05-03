package squire.Users;
/**
 * A Project finder
 */

import com.avaje.ebean.Finder;
import squire.Users.User;
import squire.Users.query.QProject;

import java.util.UUID;

public class ProjectFinder extends Finder<Long, User> {
    public ProjectFinder() {
        super(User.class);
    }

    public QProject where() {
        return new QProject(db());
    }
}

