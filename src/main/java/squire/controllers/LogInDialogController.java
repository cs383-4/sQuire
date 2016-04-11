package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ResourceBundle;
import java.net.URL;

/**
 * Created by Domn on 4/4/2016.
 */
public class LogInDialogController implements Initializable
{
    @FXML private ImageView avatarImageView;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;
    @FXML private CheckBox keepMeLoggedInCheckBox;
    @FXML private Button cancelButton;
    @FXML private Button logInButton;
    @FXML private Hyperlink registerHyperlink;
    @FXML private Hyperlink forgotPasswordHyperlink;

    private Stage thisStage;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Perform any initialization steps here.

    }

    @FXML private void onCancelButtonClick(ActionEvent event)
    {
        thisStage = (Stage)cancelButton.getScene().getWindow();
        System.out.println("Cancel button clicked.");
        thisStage.close();
    }

    @FXML private void onLogInButtonClick(ActionEvent event)
    {
        thisStage = (Stage)logInButton.getScene().getWindow();
        System.out.println("Log in button clicked.");
        thisStage.close();
    }

    @FXML private void onRegisterHyperlinkClick(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader();
        Stage dialogStage = new Stage();
        Parent root = null;
        dialogStage.setTitle("Register for sQuire");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(registerHyperlink.getScene().getWindow());
        dialogStage.setResizable(false);
        try
        {
            root = loader.load(getClass().getResource("/fxml/RegisterDialog.fxml"));
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
