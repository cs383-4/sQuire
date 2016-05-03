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
    // Database code


    @Column()
    @OneToOne
    private String primaryFile;

    /**
     * The list of source code files in this project.
     */
    private ArrayList<File> fileList = new ArrayList<>();

    @Column()
    private String projectName;

    @Column()
    private String projectDescription;

    private File entryPointClassFile;
    private UUID projectUuid = UUID.randomUUID();

    @Column()
    @ManyToOne
    private User projectOwner;

    @Column()
    private String projectPath;

    /**
     * Project class constructor.
     * @param name The string name of the Project.
     * @param owner The User owner of the Project.
     * @param path The string fully qualified path to the project on the local machine.
     */
    public Project(String name, User owner, String path, String description, File initialFile)
    {
        projectName = name;
        projectDescription = description;
        projectOwner = owner;
        projectPath = path;
        fileList.add(initialFile);
        entryPointClassFile = initialFile;
//        this.save();
    }

    /**
     * Project class constructor.
     * @param name The string name of the Project.
     * @param owner The User owner of the Project.
     * @param path The string fully qualified path to the project on the local machine.
     * @param description The project's string description.
     * @param importedFiles An ArrayList of JavaSourceFromString files to be imported with the project.
     */
    public Project(String name, User owner, String path, String description, ArrayList<File> importedFiles, File entryPointFile)
    {
        projectName = name;
        projectDescription = description;
        projectOwner = owner;
        projectPath = path;
        fileList = importedFiles;
        entryPointClassFile = entryPointFile;
//        this.save();
    }

    public void setProjectName(String name) { projectName = name; }
    public void setProjectOwner(User owner) { projectOwner = owner; }
    public void setProjectPath(String path) { projectPath = path; }
    public void setEntryPointClassFile(File file) { entryPointClassFile = file; }
    public String getProjectName() { return projectName; }
    public User getProjectOwner() { return projectOwner; }
    public String getProjectPath() { return projectPath; }
    public File getEntryPointClassFile() { return entryPointClassFile; }
    public ArrayList<File> getFileList() { return fileList;}
    public UUID getProjectUuid() {return projectUuid;}

}