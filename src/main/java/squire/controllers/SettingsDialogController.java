package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import squire.Users.PropertiesController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Domn Werner on 4/22/2016.
 */
public class SettingsDialogController implements Initializable
{
    @FXML private TextField jdkLocationTextBox;
    @FXML private Button browseButton;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;
    private PropertiesController pc;
    private Stage thisStage;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        pc = PropertiesController.getPropertiesController();
        jdkLocationTextBox.setText(pc.getProp("jdkLocation"));
        pc = PropertiesController.getPropertiesController();
    }

    @FXML private void onBrowseButtonClick(ActionEvent event)
    {
        Stage stage = null;
        stage = (Stage) browseButton.getScene().getWindow();
        DirectoryChooser dirChoose = new DirectoryChooser();
        dirChoose.setTitle("Choose JDK Location");
        File selectedDir = dirChoose.showDialog(stage);
        if (selectedDir != null)
        {
            jdkLocationTextBox.setText(selectedDir.getPath());
        }
    }

    @FXML private void onCancelButtonClick(ActionEvent event)
    {
        thisStage = (Stage)cancelButton.getScene().getWindow();
        thisStage.close();
    }

    @FXML private void onSaveButtonClick(ActionEvent event)
    {
        pc.setProp("jdkLocation", jdkLocationTextBox.getText());
        pc.saveProps();
        thisStage = (Stage) saveButton.getScene().getWindow();
        thisStage.close();
    }
}
