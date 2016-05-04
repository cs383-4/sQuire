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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import squire.CustomViews.ListViewCell;
import squire.Networking.ProjectData;

import java.net.URL;
import java.util.*;

/**
 * Created by Domn on 4/4/2016.
 */

public class ProjectBrowsingController implements Initializable
{
    @FXML private Button backButton;
    @FXML private ListView projectsListView;
    private List<ProjectData> projectDataList = new ArrayList<>();
    ObservableList observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setListView();
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

    private void setListView()
    {

        observableList.setAll(projectDataList);
        projectsListView.setItems(observableList);
        projectsListView.setCellFactory((Callback<ListView<ProjectData>, ListCell<ProjectData>>) listVIew -> new ListViewCell());
    }
}