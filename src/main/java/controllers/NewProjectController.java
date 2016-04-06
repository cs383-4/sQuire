package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Created by MattDaniel on 3/31/16.
 */

public class NewProjectController
{
    @FXML private Button nextButton;
    @FXML private Button backButton;
    @FXML private Button finishButton;
    @FXML private Button cancelButton1;
    @FXML private Button cancelButton2;
    @FXML public TabPane tabPane;
    @FXML public Tab projectDetailsTab;
    @FXML public Tab projectSettingsTab;

    @FXML private void nextButtonClicked(ActionEvent event)
    {
        //System.out.println(event.getSource());
        if (event.getSource() == nextButton) {
            tabPane.getSelectionModel().select(projectSettingsTab);
        }
    }

    @FXML private void backButtonClicked(ActionEvent event)
    {
       // System.out.println(event.getSource());
        if (event.getSource() == backButton)
        {
            if (event.getSource() == backButton) {
                tabPane.getSelectionModel().select(projectDetailsTab);
            }
        }
    }

    @FXML private void finishButtonClicked(ActionEvent event)
    {

        if (event.getSource() == finishButton)
        {
            FXMLLoader loader = new FXMLLoader();
            Stage stage = (Stage) finishButton.getScene().getWindow();
            try
            {
                Parent root = loader.load(getClass().getResource("/fxml/Editor.fxml"));
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

    @FXML private void cancelButtonClicked(ActionEvent event)
    {
        if (event.getSource() == cancelButton1 || event.getSource() == cancelButton2)
        {
            FXMLLoader loader = new FXMLLoader();
            Stage stage = (Stage) cancelButton1.getScene().getWindow();
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
//    @FXML
//    private void onNewProjectHyperlinkClick(ActionEvent event)
//    {
//        Stage stage = null;
//        Parent root = null;
//        if (event.getSource() == newProjectHyperlink)
//        {
//            FXMLLoader loader = new FXMLLoader();
//            stage = (Stage) newProjectHyperlink.getScene().getWindow();
//            try
//            {
//                root = loader.load(getClass().getResource("/fxml/NewProject.fxml"));
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//
//        if (root != null)
//        {
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//        else
//        {
//            System.out.println("Null scene.");
//        }
//    }
}