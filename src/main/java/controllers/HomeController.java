package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.applet.Main;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Domn Werner on 3/28/2016.
 */
public class HomeController
{
    @FXML
    private Hyperlink newProjectHyperlink;

    @FXML
    private Hyperlink openProjectHyperlink;

    @FXML
    private Hyperlink browseProjectsHyperlink;

    @FXML
    private Hyperlink settingsHyperlink;

    @FXML
    private Hyperlink registerHyperlink;

    @FXML
    private Hyperlink logInHyperlink;

    @FXML
    private void onNewProjectHyperlinkClick(ActionEvent event)
    {
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == newProjectHyperlink)
        {
            FXMLLoader loader = new FXMLLoader();
            stage = (Stage) newProjectHyperlink.getScene().getWindow();
            stage.setResizable(false);
            try
            {
                root = loader.load(getClass().getResource("/fxml/NewProject.fxml"));

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

    @FXML
    private void onBrowseProjectsHyperlinkClick(ActionEvent event)
    {
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == browseProjectsHyperlink)
        {
            FXMLLoader loader = new FXMLLoader();
            stage = (Stage) browseProjectsHyperlink.getScene().getWindow();
            try
            {
                root = loader.load(getClass().getResource("/fxml/ProjectBrowsing.fxml"));
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

    @FXML
    private void onLogInHyperlinkClick(ActionEvent event)
    {
        if (event.getSource() == logInHyperlink)
        {
            FXMLLoader loader = new FXMLLoader();
            Stage dialogStage = new Stage();
            Parent root = null;
            try
            {
                dialogStage.setTitle("Log in to sQuire");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(logInHyperlink.getScene().getWindow());
                dialogStage.setResizable(false);
                root = loader.load(getClass().getResource("/fxml/LogInDialog.fxml"));
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }




}
