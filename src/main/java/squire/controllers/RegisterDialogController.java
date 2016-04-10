package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by Domn on 4/4/2016.
 */
public class RegisterDialogController
{
    @FXML private ImageView avatarImageView;
    @FXML private TextField emailTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField1;
    @FXML private PasswordField passwordPasswordField2;
    @FXML private Button cancelButton;
    @FXML private Button registerButton;

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

    @FXML private void onChangeAvatarButtonClick(ActionEvent event)
    {

    }

    @FXML private void onRegisterButtonClicked(ActionEvent event)
    {
        thisStage = (Stage)registerButton.getScene().getWindow();
        System.out.println("Log in button clicked.");

        /*
            Perform registration code here...
        */

        thisStage.close();
    }
}