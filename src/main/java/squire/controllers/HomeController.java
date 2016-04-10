package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Domn Werner on 3/28/2016.
 */
public class HomeController
{
    @FXML private Hyperlink newProjectHyperlink;

    @FXML private Hyperlink openProjectHyperlink;

    @FXML private Hyperlink browseProjectsHyperlink;

    @FXML private Hyperlink settingsHyperlink;

    @FXML private Hyperlink registerHyperlink;

    @FXML private Hyperlink logInHyperlink;

    @FXML
    private void onNewProjectHyperlinkClick(ActionEvent event)
    {
        if (event.getSource() == newProjectHyperlink)
        {
            FXMLLoader loader = new FXMLLoader();
            Stage stage = (Stage) newProjectHyperlink.getScene().getWindow();
            stage.setResizable(false);
            try
            {
               // Parent root = loader.load(getClass().getResource("/fxml/NewProject.fxml"));
                Parent root = loader.load(getClass().getResource("/fxml/NewProject2.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onBrowseProjectsHyperlinkClick(ActionEvent event)
    {
        if (event.getSource() == browseProjectsHyperlink)
        {
            FXMLLoader loader = new FXMLLoader();
            Stage stage = (Stage) browseProjectsHyperlink.getScene().getWindow();
            try
            {
                Parent root = loader.load(getClass().getResource("/fxml/ProjectBrowsing.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
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
            dialogStage.setTitle("Log in to sQuire");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(logInHyperlink.getScene().getWindow());
            dialogStage.setResizable(false);
            try
            {
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