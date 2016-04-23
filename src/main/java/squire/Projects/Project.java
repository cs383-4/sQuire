package squire.Projects;

import squire.BaseModel;
import squire.Users.User;

import java.io.File;
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
    private ArrayList<File> fileList = new ArrayList<>();
    private String projectName;
    private String projectDescription;
    private File entryPointClassFile;
    private UUID projectUuid = UUID.randomUUID();
    private User projectOwner;
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
    public UUID getProjectUuid() {return projectUuid;};

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