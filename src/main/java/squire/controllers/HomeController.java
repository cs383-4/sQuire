package squire.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Domn Werner on 3/28/2016.
 */
public class HomeController implements Initializable
{
    @FXML private Hyperlink newProjectHyperlink;
    @FXML private Hyperlink openProjectHyperlink;
    @FXML private Hyperlink browseProjectsHyperlink;
    @FXML private Hyperlink settingsHyperlink;
    @FXML private Hyperlink registerHyperlink;
    @FXML private Hyperlink logInHyperlink;
    @FXML private ImageView avatarImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Since ImageViews don't have their own onAction event, I created my own event/handler lambda here.
        // This event handler will be called whenever the avatarImageView is clicked.
        avatarImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onAvatarImageViewClick());
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
                dialogStage.setTitle("Log in to sQuire");
                dialogStage.showAndWait();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

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
                stage.setTitle("New Project");
                stage.setHeight(460);
                stage.setWidth(610);
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
                stage.setTitle("Browse Projects");
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }



    @FXML private void onAvatarImageViewClick()
    {
        FXMLLoader loader = new FXMLLoader();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("User Profile");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(avatarImageView.getScene().getWindow());
        dialogStage.setResizable(false);
        try
        {
            Parent root = loader.load(getClass().getResource("/fxml/Preferences2.fxml"));
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