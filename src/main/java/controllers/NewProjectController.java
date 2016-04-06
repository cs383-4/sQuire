package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by MattDaniel on 3/31/16.
 */
public class NewProjectController
{
    @FXML private Button nextButton;
    @FXML private Button backButton;
    @FXML private Button finishButton;
    @FXML private Button cancelButton1;
    @FXML private Button cancelButton2;
    @FXML private Button importFilesButton;
    @FXML public TabPane tabPane;
    @FXML public Tab projectDetailsTab;
    @FXML public Tab projectSettingsTab;
    @FXML Parent root;


    @FXML
    private void importFilesButtonClicked(ActionEvent event)
    {
        Stage stage = null;
        stage = (Stage) importFilesButton.getScene().getWindow();
        //System.out.println(event.getSource());
        if (event.getSource() == importFilesButton) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Import File(s)");
           File selectedFile = fileChooser.showOpenDialog(stage);
            System.out.println(selectedFile.getName());
        }
    }

    @FXML
    private void nextButtonClicked(ActionEvent event)
    {
        //System.out.println(event.getSource());
        if (event.getSource() == nextButton) {
            tabPane.getSelectionModel().select(projectSettingsTab);
        }
    }



    @FXML private void backButtonClicked(ActionEvent event)
    {
       // System.out.println(event.getSource());
        if (event.getSource() == backButton)
        {
            if (event.getSource() == backButton) {
                tabPane.getSelectionModel().select(projectDetailsTab);
            }
        }
    }

    @FXML private void finishButtonClicked(ActionEvent event)
    {
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == finishButton)
        {
            FXMLLoader loader = new FXMLLoader();
            stage = (Stage) finishButton.getScene().getWindow();
            stage.setResizable(true);
            try
            {
                root = loader.load(getClass().getResource("/fxml/Editor.fxml"));
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
            stage.show();
        }
        else
        {
            System.out.println("Null scene.");
        }

    }


    @FXML private void cancelButtonClicked(ActionEvent event)
    {
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == cancelButton1 || event.getSource() == cancelButton2)
        {
            FXMLLoader loader = new FXMLLoader();
            stage = (Stage) cancelButton1.getScene().getWindow();
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
            stage.show();
        }
        else
        {
            System.out.println("Null scene.");
        }

    }

}
