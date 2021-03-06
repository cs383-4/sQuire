package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import squire.Main;
import squire.Networking.Request;
import squire.Networking.Response;
import squire.Users.PropertiesController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Domn Werner on 4/19/2016.
 */
public class NewProjectController3 implements Initializable {
    @FXML
    private TextField projectTitleTextField;
    @FXML
    private TextArea projectDescriptionTextArea;
    @FXML
    private TextField locationTextField;
    @FXML
    private Button browseButton;
    @FXML
    private Button finishButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button backButton;

    public String projectName;
    public String projectLocation;
    public String projectDescription;
    public String currentSession;
    public ArrayList<File> projectFiles = new ArrayList<File>();
    private PropertiesController pc = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentSession = Main.getSessionID();
        projectLocation = Main.getProjectsDir();
        locationTextField.setText(projectLocation);
        pc = PropertiesController.getPropertiesController();
    }

    @FXML
    private void onBrowseButtonClick(ActionEvent event) {
        if (event.getSource() == browseButton) {
            Stage stage = null;
            stage = (Stage) browseButton.getScene().getWindow();
            loadBrowser(stage);
        }
    }

    //Sends back to home screen
    @FXML
    private void onBackButtonClick(ActionEvent event) {

        if (event.getSource() == backButton || event.getSource() == cancelButton) {
            loadHomeScene();
        }
    }

    @FXML
    private void onFinishButtonClick(ActionEvent event) {
        // Do not allow an invalid name
        if (projectTitleTextField.getText().isEmpty()) {
            projectTitleTextField.setPromptText("Please enter a project name");
        } else {
            projectName = projectTitleTextField.getText();
            projectDescription = projectDescriptionTextArea.getText();

            createProject();
            loadEditorScene();
        }
    }


    //__________________________________________________________________________________________
//Helper functions
    public void createProject() {
        Response res = new Request("project/addProject")
                .set("sessionID", Main.getSessionID())
                .set("name", projectName)
                .set("description", projectDescription)
                .send();
        Main.setProjectID((String) res.get("projectUUID"));
        //make sure the project name is null, so it will be repopulated. This is needed if changing projects without restarting
        Main.setProjectName(null);
    }

    public void loadEditorScene() {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = (Stage) finishButton.getScene().getWindow();
        //stage.setResizable(false);
        try {
            Parent root = loader.load(getClass().getResource("/fxml/Editor.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("sQuire Editor - Project " + Main.getProjectName());
            stage.setScene(scene);
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            int fromEdge = 50;

            //set Stage boundaries to visible bounds of the main screen
            stage.setX(primaryScreenBounds.getMinX() + fromEdge / 2);
            stage.setY(primaryScreenBounds.getMinY() + fromEdge / 2);
            stage.setWidth(primaryScreenBounds.getWidth() - fromEdge);
            stage.setHeight(primaryScreenBounds.getHeight() - fromEdge);
            stage.setResizable(true);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Go back to the home screen
    public void loadHomeScene() {
        Stage stage = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        stage = (Stage) backButton.getScene().getWindow();
        try {
            root = loader.load(getClass().getResource("/fxml/Home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (root != null) {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(600);
            stage.setTitle("sQuire Home");
            stage.show();
        } else {
            System.out.println("Null scene.");
        }
    }

    //Load a directory browser
    public void loadBrowser(Stage stage) {
        DirectoryChooser dirChoose = new DirectoryChooser();
        dirChoose.setInitialDirectory(new File(Main.getProjectsDir()));
        dirChoose.setTitle("Choose Project Location");
        File selectedDir = dirChoose.showDialog(stage);
        if (selectedDir != null) {
            locationTextField.setText(selectedDir.getPath());
            projectLocation = selectedDir.getAbsolutePath();
        }
    }
}
