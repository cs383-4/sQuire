package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by Domn on 4/4/2016.
 */
public class LogInDialogController
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

    @FXML private void initialize()
    {
        // Initialize anything here if needed.
    }

    @FXML private void onCancelButtonClick(ActionEvent event)
    {
        thisStage = (Stage)cancelButton.getScene().getWindow();
        System.out.println("Cancel button clicked.");
        thisStage.close();
    }

    @FXML private void onLogInButtonClicked(ActionEvent event)
    {
        thisStage = (Stage)logInButton.getScene().getWindow();
        System.out.println("Log in button clicked.");
        thisStage.close();
    }
}
