package squire.Users;

/**
 * Implements files to be used by the project database
 */

import squire.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.File;

@Entity
//some databases have user as a reserved word, so following ebean examples, prefix tables with "o_"
@Table(name = "o_project_file")
public class ProjectFile extends BaseModel {
    public static final ProjectFileFinder find = new ProjectFileFinder();

    @Column()
    @ManyToOne
    private Project project;

    @Column()
    private File file;

    @Column()
    private String path;

    @Column()
    private String description;

}
