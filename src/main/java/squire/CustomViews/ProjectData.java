package squire.CustomViews;

import java.util.UUID;

/**
 * Created by Domn Werner on 5/3/2016.
 *
 * This class will hold the relevant data for the custom ListView in the project browser.
 */
public class ProjectData
{
    UUID projectId;

    public UUID getProjectid()
    {
        return projectId;
    }

    public void setProjectid(UUID projectid)
    {
        this.projectId = projectid;
    }

    String projectName;

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    String projectDescription;

    public String getProjectDescription()
    {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription)
    {
        this.projectDescription = projectDescription;
    }

    public ProjectData(UUID id, String name, String desc)
    {
        projectId = id;
        projectName = name;
        projectDescription = desc;
    }
}