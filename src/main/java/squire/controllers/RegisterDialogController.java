package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import squire.Users.User;

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

        if(passwordPasswordField1.getText().equals(passwordPasswordField2.getText()) ) {

            if(User.find.where().username.equalTo(usernameTextField.getText()).findUnique() == null) {

                User u = new User(usernameTextField.getText(), passwordPasswordField1.getText());

                u.save();

                thisStage.close();
            }
        }



    }
}