package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import squire.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    private String projectLocation;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

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
        System.out.println("Finish button clicked.");
    }
}
