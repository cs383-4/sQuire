package squire.Users;
/**
 * A ProjectFile finder.
 */

import com.avaje.ebean.Finder;
import squire.Users.query.QProjectFile;

public class ProjectFileFinder extends Finder<Long, ProjectFile> {
    public ProjectFileFinder() {
        super(ProjectFile.class);
    }

    public QProjectFile where() {
        return new QProjectFile(db());
    }

}
