package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import squire.Main;
import squire.Networking.Request;
import squire.Networking.Response;
import squire.Users.PropertiesController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Domn on 4/4/2016.
 */
public class LogInDialogController implements Initializable
{
    @FXML
    private ImageView avatarImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private CheckBox keepMeLoggedInCheckBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button logInButton;
    @FXML
    private Hyperlink registerHyperlink;
    @FXML
    private Hyperlink forgotPasswordHyperlink;

    private Stage thisStage;
    private PropertiesController pc;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Perform any initialization steps here.
        pc = PropertiesController.getPropertiesController();
        usernameTextField.setText(pc.getProp("username"));
        passwordPasswordField.setText(pc.getProp("password"));

        String signIn = pc.getProp("signInCheckbox");
        if(signIn == null)
        {
            signIn = "";
        }
        if (signIn.equals("true"))
        {
            keepMeLoggedInCheckBox.setSelected(true);
        }
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event)
    {
        thisStage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("Cancel button clicked.");
        thisStage.close();
    }

    @FXML
    private void onLogInButtonClick(ActionEvent event)
    {


        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        sendRequest(username, password);
    }



    public void sendRequest(String username, String password)
    {

        thisStage = (Stage) logInButton.getScene().getWindow();
        Response res = new Request("user/login")
                .set("username", username)
                .set("password", password)
                .send();

        if (res.getSuccess())
        {
            Main.setSessionID((String)res.get("sessionID"));
            System.out.println("Login successful.");
            Main.userNotLoggedIn.setValue(false);

            //Save the login option if checked
            if (keepMeLoggedInCheckBox.isSelected())
            {
                pc.setProp("username", usernameTextField.getText());
                pc.setProp("password", passwordPasswordField.getText());
                pc.setProp("signInCheckbox", "true");
                pc.saveProps();
            }

            HomeController.userName = usernameTextField.getText();
            thisStage.close();
        }
        else
        {
            //incorrect password
        }
    }

    @FXML
    private void onRegisterHyperlinkClick(ActionEvent event)
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
