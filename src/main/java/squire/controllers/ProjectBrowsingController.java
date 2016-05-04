package squire.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.junit.runner.Result;
import squire.CustomViews.ListViewCell;
import squire.Main;
import squire.Networking.ProjectData;
import squire.Networking.Request;
import squire.Networking.Response;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Created by Domn on 4/4/2016.
 */

public class ProjectBrowsingController implements Initializable
{
    @FXML private Button backButton;
    @FXML private ListView projectsListView;
    @FXML private Button openButton;
    private List<ProjectData> projectDataList;
    ObservableList observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        populateProjectDataList();
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

    private void populateProjectDataList()
    {
        Response res = new Request("project/getProjectList").send();
        projectDataList = (List<ProjectData>)res.get("projects");
    }

    @FXML private void onOpenButtonClick(ActionEvent event)
    {
        ProjectData selectedProject = (ProjectData)projectsListView.getSelectionModel().getSelectedItem();
        if (selectedProject != null)
        {
            Main.projectID = selectedProject.projectUUID;
            Main.setProjectName(null);
            new Request("project/addRecentProject")
                    .set("sessionID", Main.getSessionID())
                    .set("projectUUID", Main.getProjectID())
                    .send();
            loadEditor();
        }
        else
        {
            throw new RuntimeException("Selected project is null.");
        }
    }

    private void loadEditor()
    {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("/fxml/Editor.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)projectsListView.getScene().getWindow();
            stage.setTitle("sQuire Editor - Project " + Main.getProjectName());
            stage.setScene(scene);
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            int fromEdge = 50;

            //set Stage boundaries to visible bounds of the main screen
            stage.setX(primaryScreenBounds.getMinX() + fromEdge / 2);
            stage.setY(primaryScreenBounds.getMinY() + fromEdge / 2);
            stage.setWidth(primaryScreenBounds.getWidth() - fromEdge);
            stage.setHeight(primaryScreenBounds.getHeight() - fromEdge);
            stage.setResizable(true);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}