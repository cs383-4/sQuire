package squire.Users;
/**
 * Implements a project storage model.
 */

import squire.BaseModel;
import squire.Projects.JavaSourceFromString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "o_project")
public class Project extends BaseModel {
    public static final ProjectFinder find = new ProjectFinder();

    @Column(nullable = false, unique = true)
    private UUID token;

    @Column(nullable = false)
    @ManyToOne
    private User owner;

    @Column()
    private String name;

    @Column()
    private String path;

    @Column()
    private String description;

    @Column()
    private ArrayList<JavaSourceFromString> importedFiles;

    @Column()
    private Integer primaryFileID;


    public String getToken() {
        return token.toString();
    }

    private void generateToken() {
        this.token = UUID.randomUUID();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
