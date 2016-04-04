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

    private boolean okClicked = false;

    private Stage dialogStage;

    @FXML private void initialize()
    {

    }

    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked()
    {
        return okClicked;
    }

    @FXML private void okButtonClicked(ActionEvent event)
    {
        System.out.println("Ok Button Clicked.");
    }

    @FXML private void cancelButtonClicked(ActionEvent event)
    {
        dialogStage.close();
    }
}
