package squire.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Domn on 4/4/2016.
 */

public class ProjectBrowsingController implements Initializable
{
    @FXML private Button backButton;
    @FXML private ListView projectsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ArrayList<HBox> projectCellsList = new ArrayList<>();
        ObservableList<HBox> projectCellsObservableList = FXCollections.observableList(projectCellsList);
        FXMLLoader loader = new FXMLLoader();
        try
        {
            projectCellsList.add(loader.load(getClass().getResource("/fxml/ProjectListViewItem")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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