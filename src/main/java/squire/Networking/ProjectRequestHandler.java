package squire.Networking;

import squire.Users.Session;
import squire.Users.User;
import squire.Users.Project;

import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * The request handler dealing with all things Users
 */
@Route("project/")
class ProjectRequestHandler {
    @Route("addProject")
    static void addProject(Request req, Response res) {
        User u = Session.find.activeSession((String) req.get("sessionID")).getUser();
        Project p = new Project();
        p.setProjectName((String) req.get("name"));
        p.setProjectOwner(u);
        p.setProjectDescription((String) req.get("description"));
        p.save();
        res.set("projectUUID", p.getProjectUuid());
    }

    @Route("getProjectName")
    static void getProjectName(Request req, Response res) {
        Project p = Project.find.where().projectUuid.equalTo((String) req.get("projectUUID")).findUnique();
        if(p == null) {
            //can't find project
            res.setFail();
        }
        res.set("name", p.getProjectName());
    }

    @Route("getProjectsWithUid")
    static void getProjectsWithUid (Request req, Response res)
    {
    }

}