package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;



/**
 * Created by Domn Werner on 4/22/2016.
 */
public class SettingsDialogController implements Initializable
{
    @FXML private TextField jdkLocationTextField;
    @FXML private Button browseButton;
    @FXML private Button cancelButton;
    @FXML private Button finishButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML private void onBrowseButtonClick(ActionEvent event)
    {

    }

    @FXML private void onCancelButtonClick(ActionEvent event)
    {

    }

    @FXML private void onSaveButtonClick(ActionEvent event)
    {

    }
}
