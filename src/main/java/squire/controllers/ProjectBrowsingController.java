package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Domn on 4/4/2016.
 */

public class ProjectBrowsingController implements Initializable
{
    @FXML private Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML private void onBackButtonClick(ActionEvent event)
    {
        if (event.getSource() == backButton)
        {
            FXMLLoader loader = new FXMLLoader();
            Stage stage = (Stage) backButton.getScene().getWindow();
            try
            {
                Parent root = loader.load(getClass().getResource("/fxml/Home.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}