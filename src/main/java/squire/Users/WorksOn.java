package squire.Users;

import squire.BaseModel;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Matthew Daniel on 5/3/2016.
 */

@Entity
@Table(name = "o_works_on")
public class WorksOn extends BaseModel
{
    @Column(nullable = false)
    @ManyToOne
    private User uid;

    @Column(nullable = false)
    @OneToOne
    private Project pid;

    /**
     * The list of source code files in this project.
     */



    public WorksOn(User userId, Project projID)
    {
        uid = userId;
        pid = projID;
    }

    public WorksOn() {
        //empty constructor
    }

    public Project getProjectID() {
        return pid;
    }

    public void setProjectId(Project projId) {
        this.pid = projId;
    }


    public void setUserId(User userId) { uid = userId; }
    public User getUserId() {return uid; }


}