package squire.Networking;

import java.io.Serializable;

/**
 * Created by brandon on 5/3/16.
 */
public class ProjectData implements Serializable {
    public ProjectData(String name, String projectUUID, String description) {
        this.name = name;
        this.projectUUID = projectUUID;
        this.description = description;
    }

    public String name;
    public String projectUUID;
    public String description;
}
