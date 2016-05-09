package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import squire.Main;
import squire.Networking.Request;
import squire.Networking.Response;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Domn Werner on 4/10/2016.
 */
public class PreferencesDialogController implements Initializable
{
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField newEmailTextField;
    @FXML private TextField confirmEmailTextField;
    @FXML private Button closeButton;
    @FXML private Button submitButton;
    @FXML private Label usernameLabel;
    @FXML private Label emailLabel;

    private Stage thisStage;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Response res = new Request("user/getUsernameFromSessionId").set("sessionID", Main.getSessionID()).send();
        String username = (String)res.get("username");
        usernameLabel.setText((String)res.get("username"));

        Response emailres = new Request("user/getEmailFromUsername").set("sessionID", Main.getSessionID()).send();
        String email = (String)emailres.get("email");
        emailLabel.setText((String)emailres.get("email"));

    }

    @FXML private void onCloseButtonClick(ActionEvent event)
    {
        thisStage = (Stage)closeButton.getScene().getWindow();
        System.out.println("Close button clicked.");
        thisStage.close();
    }

    @FXML private void onSubmitButtonClick(ActionEvent event)
    {
        thisStage = (Stage)submitButton.getScene().getWindow();
        System.out.println("Submit button clicked.");
        if (newPasswordField.getText().equals(confirmPasswordField.getText()) && !newPasswordField.getText().isEmpty()
                && !confirmPasswordField.getText().isEmpty() ) {
            Response res = new Request("user/ChangePassword")
                    .set("sessionID", Main.getSessionID())
                    .set("password", newPasswordField.getText())
                    .send();

            if (res.getSuccess())
            {
                System.out.println("Password Changed.");
                thisStage.close();
            }
            else
            {
                //user already exists
            }
        }

        if(newEmailTextField.getText().equals(confirmEmailTextField.getText()) && !newEmailTextField.getText().isEmpty()
                && !confirmEmailTextField.getText().isEmpty())
        {
            Response res = new Request("user/ChangeEmail")
                    .set("sessionID", Main.getSessionID())
                    .set("Email", newEmailTextField.getText())
                    .send();

            if (res.getSuccess())
            {
                System.out.println("Email Changed.");
                thisStage.close();
            }
            else
            {
                //user already exists
            }

        }
        //thisStage.close();
    }
}
