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
import squire.Projects.Project;
import squire.Users.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Domn Werner on 4/19/2016.
 */
public class NewProjectController3 implements Initializable
{
    @FXML private TextField projectTitleTextField;
    @FXML private TextArea projectDescriptionTextArea;
    @FXML private TextField locationTextField;
    @FXML private Button browseButton;
    @FXML private Button finishButton;
    @FXML private Button cancelButton;
    @FXML private Button backButton;

    private String projectName;
    private String projectLocation;
    private String projectDescription;
    private Project createdProject;
    private User currentUser;
    private ArrayList<File> projectFiles = new ArrayList<File>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        currentUser = Main.getCurrentUser();
        projectLocation = Main.getProjectsDir();
        locationTextField.setText(projectLocation);
    }

    @FXML private void onBrowseButtonClick(ActionEvent event)
    {
        if (event.getSource() == browseButton)
        {
            Stage stage = null;
            stage = (Stage) browseButton.getScene().getWindow();
            DirectoryChooser dirChoose = new DirectoryChooser();
            dirChoose.setInitialDirectory(new File(Main.getProjectsDir()));
            dirChoose.setTitle("Choose Project Location");
            File selectedDir = dirChoose.showDialog(stage);
            if (selectedDir != null)
            {
                locationTextField.setText(selectedDir.getPath());
                projectLocation = selectedDir.getAbsolutePath();
            }
        }
    }

    //Sends back to home screen
    @FXML private void onBackButtonClick(ActionEvent event)
    {
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == backButton || event.getSource() == cancelButton)
        {
            FXMLLoader loader = new FXMLLoader();
            stage = (Stage) backButton.getScene().getWindow();
            try
            {
                root = loader.load(getClass().getResource("/fxml/Home.fxml"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (root != null)
        {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(600);
            stage.setTitle("sQuire Home");
            stage.show();
        }
        else
        {
            System.out.println("Null scene.");
        }
    }

    @FXML private void onFinishButtonClick(ActionEvent event)
    {

        String fileLocation = initProjectFields();
        copyMainFile(fileLocation);
        loadScene();

    }

//Helper functions
    public String initProjectFields()
    {
        projectName = projectTitleTextField.getText();
        projectDescription = projectDescriptionTextArea.getText();
        projectLocation = locationTextField.getText() + File.separator + projectName;

        // Placeholder.
        String entryPointClassName = "Main.java";
        String fileLocation = projectLocation + File.separator + entryPointClassName;
        File projectDirectory = new File(projectLocation);
        projectDirectory.mkdir();

        return fileLocation;
    }

    public void copyMainFile(String fileLocation)
    {
        File file = new File(fileLocation);
        try
        {
            if (file.createNewFile())
            {

                //System.out.println("File created: " + fileLocation);

                // Copies the dummy file over
                URL url = this.getClass().getResource("/Test_Files/Main.java");
                File mainFile = new File(url.getPath());
                Path from = mainFile.toPath();
                File toFile = new File(fileLocation);
                Path to = toFile.toPath();
                CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(from, to, options);
                projectFiles.add(toFile);





                createdProject = new Project(projectName, currentUser, projectLocation, projectDescription, projectFiles, toFile);
                currentUser.addProject(createdProject);
                currentUser.setCurrentProject(createdProject);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void loadScene()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = (Stage) finishButton.getScene().getWindow();
        //stage.setResizable(false);
        try
        {
            Parent root = loader.load(getClass().getResource("/fxml/Editor.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("sQuire Editor - Project " + currentUser.getCurrentProject().toString());
            stage.setScene(scene);
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            int fromEdge = 50;

            //set Stage boundaries to visible bounds of the main screen
            stage.setX(primaryScreenBounds.getMinX() + fromEdge/2);
            stage.setY(primaryScreenBounds.getMinY() + fromEdge/2);
            stage.setWidth(primaryScreenBounds.getWidth() - fromEdge);
            stage.setHeight(primaryScreenBounds.getHeight() - fromEdge);
            stage.setResizable(true);
            stage.show();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

