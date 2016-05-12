package squire.Users;

/**
 * Implements files to be used by the project database
 */

import squire.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//some databases have user as a reserved word, so following ebean examples, prefix tables with "o_"
@Table(name = "o_project_file")
public class ProjectFile extends BaseModel {
    public static final ProjectFileFinder find = new ProjectFileFinder();

    @ManyToOne()
    private Project project;
    private String name;

    public ProjectFile(String name) {
        this.name = name;
    }
        public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
