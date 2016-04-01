package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by MattDaniel on 3/31/16.
 */
public class NewProjectController
{
    @FXML
    private Button nextButton;



    @FXML
    private void nextButtonClicked(ActionEvent event)
    {
        Stage stage = null;
        Parent root = null;
//        System.out.println(event.getSource());
        if (event.getSource() == nextButton)
        {
            FXMLLoader loader = new FXMLLoader();
            stage = (Stage) nextButton.getScene().getWindow();
            try
            {
                root = loader.load(getClass().getResource("/fxml/Editor.fxml"));
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
