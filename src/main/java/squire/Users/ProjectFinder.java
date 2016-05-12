package squire.Users;
/**
 * A Project finder
 */

import com.avaje.ebean.Finder;
import squire.Users.query.QProject;

public class ProjectFinder extends Finder<Long, Project> {
    public ProjectFinder() {
        super(Project.class);
    }

    public QProject where() {
        return new QProject(db());
    }
}

