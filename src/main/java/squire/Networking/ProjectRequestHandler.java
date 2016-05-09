package squire.Networking;

import squire.Users.ProjectFile;
import squire.Users.Session;
import squire.Users.User;
import squire.Users.Project;

import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        p.setName((String) req.get("name"));
        p.setOwner(u);
        p.setProjectDescription((String) req.get("description"));
        p.save();

        u.getWorkingOn().add(p);
        u.save();
        //create the starting file
        ProjectFile mainFile = new ProjectFile("Main.java");
        mainFile.setProject(p);
        mainFile.save();

        res.set("projectUUID", p.getProjectUuid());
    }

    @Route("addFileToProject")
    static void addFileToProject(Request req, Response res) {
        Project p = Project.find.where().projectUuid.equalTo((String) req.get("projectUUID")).findUnique();
        if (p == null) {
            //can't find project
            res.setFail();
        } else {
            ProjectFile f = new ProjectFile((String) req.get("name"));
            f.setProject(p);
            f.save();
        }
    }

    @Route("getFileName")
    static void getFileName(Request req, Response res) {
        ProjectFile f = ProjectFile.find.where().id.equalTo((int) req.get("id")).findUnique();
        if (f == null) {
            res.setFail();
        } else {
            res.set("name", f.getName());
        }
    }

    @Route("getFilesInProject")
    static void getFilesInProject(Request req, Response res) {
        Project p = Project.find.where().projectUuid.equalTo((String) req.get("projectUUID")).findUnique();
        if (p == null) {
            res.setFail();
        } else {
            ArrayList<String> fileNames = new ArrayList<>();
            for(ProjectFile f: p.getFiles()) {
                fileNames.add(f.getName());
            }
            res.set("files", fileNames);
        }
    }

    @Route("getProjectName")
    static void getProjectName(Request req, Response res) {
        Project p = Project.find.where().projectUuid.equalTo((String) req.get("projectUUID")).findUnique();
        if (p == null) {
            //can't find project
            res.setFail();
        }
        res.set("name", p.getName());
    }

    @Route("getProjectList")
    static void getProjectList(Request req, Response res) {
        ArrayList<ProjectData> projects = new ArrayList<>();
        for(Project p: Project.find.all()) {
            projects.add(new ProjectData(p.getName(), p.getProjectUuid(), p.getProjectDescription()));
        }
        res.set("projects", projects);
    }

    @Route("getUserRecentProjects")
    static void getUserRecentProjects(Request req, Response res) {
        User u = Session.find.activeSession((String) req.get("sessionID")).getUser();
        ArrayList<ProjectData> projects = new ArrayList<>();
        for(Project p: u.getWorkingOn()) {
            projects.add(new ProjectData(p.getName(), p.getProjectUuid(), p.getProjectDescription()));
        }
        res.set("projects", projects);
    }

    @Route("addRecentProject")
    static void addRecentProject(Request req, Response res) {
        User u = Session.find.activeSession((String) req.get("sessionID")).getUser();
        Project p = Project.find.where().projectUuid.equalTo((String) req.get("projectUUID")).findUnique();
        for(Project recent: u.getWorkingOn()) {
           if(p.getName().equals(recent.getName())) {
               //don't add a project if it already exists in the recent projects
               return;
           }
        }
        u.getWorkingOn().add(p);
        u.save();
    }

    @Route("getProjectsWithUid")
    static void getProjectsWithUid(Request req, Response res) {
    }

}