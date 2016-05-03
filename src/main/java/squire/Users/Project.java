package squire.Users;

import squire.BaseModel;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

    @Column()
    private String name;

    @Column()
    private String path;

   @Column()
    private String description;

    @Column()
    private ArrayList<JavaSourceFromString> importedFiles;

    @Column()
    @OneToOne
    private ProjectFile primaryFile;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Project> workingOn;


    private String projectUuid = UUID.randomUUID().toString();

    /**
     * The list of source code files in this project.
     */
    private ArrayList<File> fileList = new ArrayList<>();
    private String projectName;
    private String projectDescription;
    private User projectOwner;
    private String projectPath;

    /**
     * Project class constructor.
     * @param name The string name of the Project.
     * @param owner The User owner of the Project.
     * @param path The string fully qualified path to the project on the local machine.
     */
    public Project(String name, User owner, String path, String description)
    {
        projectName = name;
        projectDescription = description;
        projectOwner = owner;
        projectPath = path;
        //fileList.add(initialFile);
        //entryPointClassFile = initialFile;
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
//        this.save();
    }

    public Project() {
        //empty constructor
    }

    public String getProjectDescription() {
        return description;
    }

    public void setProjectDescription(String description) {
        this.description = description;
    }


    public void setProjectName(String name) { projectName = name; }
    public void setProjectOwner(User owner) { projectOwner = owner; }
    public void setProjectPath(String path) { projectPath = path; }
    public String getProjectName() { return projectName; }
    public User getProjectOwner() { return projectOwner; }
    public String getProjectPath() { return projectPath; }
    public ArrayList<File> getFileList() { return fileList;}
    public String getProjectUuid() {return projectUuid;};
    public List<Project> getWorkingOn() {
        return workingOn;
    }

    public void setWorkingOn(List<Project> workingOn) {
        this.workingOn = workingOn;
    }

    public String getMatchingFile(String s)
    {
        String file = "";
        String t;
        for (File fileName: this.getFileList())
        {
            t = fileName.getName();

            if (s.equals(t))
            {
                //     System.out.println(fileName.substring(file.lastIndexOf("/")+1));
                file = file.toString();
            }
        }
        //   System.out.println(file);
        return file;
    }
}