package squire.Projects;

import squire.BaseModel;
import squire.Users.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Domn Werner on 4/19/2016.
 */
public class Project extends BaseModel
{
    /**
     * The list of source code files in this project.
     */
    private ArrayList<JavaSourceFromString> fileList = new ArrayList<>();
    private String projectName;
    private String projectDescription;
    private String entryPointClassName;
    private UUID projectUuid = UUID.randomUUID();
    private User projectOwner;
    private String projectPath;
    /**
     * Project class constructor.
     * @param name The string name of the Project.
     * @param owner The User owner of the Project.
     * @param path The string fully qualified path to the project on the local machine.
     */
    public Project(String name, User owner, String path, String description, JavaSourceFromString initialFile)
    {
        projectName = name;
        projectDescription = description;
        projectOwner = owner;
        projectPath = path;
        fileList.add(initialFile);
        entryPointClassName = initialFile.getFileName();
    }

    /**
     * Project class constructor.
     * @param name The string name of the Project.
     * @param owner The User owner of the Project.
     * @param path The string fully qualified path to the project on the local machine.
     * @param description The project's string description.
     * @param importedFiles An ArrayList of JavaSourceFromString files to be imported with the project.
     */
    public Project(String name, User owner, String path, String description, ArrayList<JavaSourceFromString> importedFiles)
    {
        projectName = name;
        projectDescription = description;
        projectOwner = owner;
        projectPath = path;
        fileList = importedFiles;
    }

    public void setProjectName(String name) { projectName = name; }
    public void setProjectOwner(User owner) { projectOwner = owner; }
    public void setProjectPath(String path) { projectPath = path; }
    public void setEntryPointClassName(String name) { entryPointClassName = name; }
    public String getProjectName() { return projectName; }
    public User getProjectOwner() { return projectOwner; }
    public String getProjectPath() { return projectPath; }
    public String getEntryPointClassName() { return entryPointClassName; }
    public ArrayList<JavaSourceFromString> getFileList() { return fileList;}

}
