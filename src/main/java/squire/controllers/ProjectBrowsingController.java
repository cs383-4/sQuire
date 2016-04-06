package squire.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Domn on 4/4/2016.
 */
public class ProjectBrowsingController
{
    @FXML
    private Button backButton;

    @FXML
    private void onBackButtonClick(ActionEvent event)
    {
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == backButton)
        {
            FXMLLoader loader = new FXMLLoader();
            stage = (Stage) backButton.getScene().getWindow();
            try
            {
                root = loader.load(getClass().getResource("/fxml/Home.fxml"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (root != null)
        {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            System.out.println("Null scene.");
        }
    }
}
