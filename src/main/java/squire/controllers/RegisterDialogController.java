package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import squire.Networking.Request;
import squire.Networking.Response;

/**
 * Created by Domn on 4/4/2016.
 */
public class RegisterDialogController
{
    @FXML
    private ImageView avatarImageView;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField1;
    @FXML
    private PasswordField passwordPasswordField2;
    @FXML
    private Button cancelButton;
    @FXML
    private Button registerButton;

    private Stage thisStage;

    @FXML
    private void initialize()
    {
        // Initialize anything here if needed.

    }

    @FXML
    private void onCancelButtonClick(ActionEvent event)
    {
        thisStage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel button clicked.");
        thisStage.close();
    }

    @FXML
    private void onChangeAvatarButtonClick(ActionEvent event)
    {

    }

    @FXML
    private void onRegisterButtonClicked(ActionEvent event)
    {
        thisStage = (Stage)registerButton.getScene().getWindow();
        System.out.println("Register button clicked.");

        if (passwordPasswordField1.getText().equals(passwordPasswordField2.getText()))
        {
            //create a new request with route register, and send it.
            Response res = new Request("user/register")
                    .set("username", usernameTextField.getText())
                    .set("email", emailTextField.getText())
                    .set("password", passwordPasswordField1.getText())
                    .send();

            if (res.getSuccess())
            {
                System.out.println("Registration successful.");
                thisStage.close();
            }
            else
            {
                //user already exists
            }
        }
    }
}