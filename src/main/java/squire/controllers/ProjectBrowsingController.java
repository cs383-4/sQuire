package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by Domn on 4/4/2016.
 */

public class ProjectBrowsingController
{
    @FXML private Button backButton;

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