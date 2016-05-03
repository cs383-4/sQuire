package squire;

import google.mobwrite.MobWriteClient;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import squire.Networking.Request;
import squire.Networking.Response;
import squire.Users.PropertiesController;
import squire.Users.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;


public class Main extends Application implements Initializable {
    // The location of the directory that stores all the user's projects.
    private static String projectsDir = System.getProperty("user.dir") + File.separator + "Projects";
    private static User currentUser = null;
    private static PropertiesController pc;

    public static String sessionID = null;
    public static String projectID = null;
    public static String projectName = null;
    public static MobWriteClient mobwrite = null;

    public static BooleanProperty userNotLoggedIn = new SimpleBooleanProperty(true);

    public static String getProjectsDir() {
        return projectsDir;
    }
    public static String getSessionID() {
        return sessionID;
    }
    public static void setSessionID(String val) {
        sessionID = val;
    }
    public static String getProjectID() {
        return projectID;
    }
    public static void setProjectID(String val) {
        projectID = val;
    }

    public static void main(String[] args) {
        // From the 'Application' class.
        // Sets up program as a javafx application.
        System.out.println(System.getProperty("user.dir"));
        generateProjectsDir();
        createPropFileIfNeeded();
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pc = PropertiesController.getPropertiesController();
        pc.loadProps();
    }

    // Called during launch().
    @Override
    public void start(Stage stage) throws Exception {
        try {
            // This is returning null, thus the catch block is executing.
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/fxml/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("sQuire Home");
            stage.setHeight(400);
            stage.setWidth(600);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    /**
     * Generates a folder for storing user projects if doesn't already exist.
     */
    private static void generateProjectsDir() {
        File f = new File(projectsDir);
        if (!f.exists()) {
            System.out.println("Creating directory: " + projectsDir);
            boolean success = false;

            try {
                f.mkdir();
                success = true;
            } catch (Exception e) {
                System.out.println(e.toString());
                // Handle...
            }

            if (success) {
                System.out.println("Projects dir created at " + projectsDir);
            }
        }
    }

    public static String getProjectName() {
        if(projectName == null) {
            Response res = new Request("project/getProjectName").set("projectUUID", getProjectID()).send();
            projectName = (String) res.get("name");
        }
        return projectName;
    }
    private static void createPropFileIfNeeded() {
        File propFile = new File("squire_config.properties");

        try {
            propFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the mobwrite project, initilizing it if it hasn't been used yet
     */
    public static MobWriteClient getMobwriteClient() {
        if (mobwrite == null) {
            mobwrite = new MobWriteClient("http://squireserver.westus.cloudapp.azure.com/py/q.py");
            mobwrite.maxSyncInterval = 1000;
            mobwrite.minSyncInterval = 500;
        }
        return mobwrite;
    }
}