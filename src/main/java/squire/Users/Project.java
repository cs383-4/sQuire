package squire.Users;

import squire.BaseModel;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Domn Werner on 4/19/2016.
 * Database functionality added by Brian Cartwright
 */

@Entity
@Table(name = "o_project")
public class Project extends BaseModel
{
    public static final ProjectFinder find = new ProjectFinder();
    // Database code    public QSession where() {


    @Column(nullable = false)
    @ManyToOne
    private User owner;
    private String name;
    private String description;

    @OneToOne
    private ProjectFile primaryFile;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Project> workingOn;


    private String projectUuid = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectFile> files;

    public String getProjectDescription() {
        return description;
    }

    public void setProjectDescription(String description) {
        this.description = description;
    }

    public String getProjectUuid() {
        return projectUuid;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectFile> getFiles() {
        return files;
    }

    public void setFiles(List<ProjectFile> files) {
        this.files = files;
    }


}