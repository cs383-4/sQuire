package squire.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import squire.CustomViews.ListViewCell;
import squire.CustomViews.RecentListViewCell;
import squire.Main;
import squire.Networking.ProjectData;
import squire.Networking.Request;
import squire.Networking.Response;
import squire.Users.Project;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Domn Werner on 3/28/2016.
 */
public class HomeController implements Initializable
{
    @FXML private Hyperlink newProjectHyperlink;
    @FXML private Hyperlink openProjectHyperlink;
    @FXML private Hyperlink browseProjectsHyperlink;
    @FXML private Hyperlink settingsHyperlink;
    @FXML private Hyperlink registerHyperlink;
    @FXML private Hyperlink logInHyperlink;
    @FXML private ImageView avatarImageView;
    @FXML private ListView recentProjectsListView;
    @FXML private Label userNameLabel;

    public static String userName = "";

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Since ImageViews don't have their own onAction event, I created my own event/handler lambda here.
        // This event handler will be called whenever the avatarImageView is clicked.
        avatarImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onAvatarImageViewClick());
        if (Main.getSessionID() != null)
        {
            Platform.runLater(() -> logInHyperlink.setVisible(false));
            Platform.runLater(() -> registerHyperlink.setVisible(false));

            Response res = new Request("user/getUsernameFromSessionId").set("sessionID", Main.getSessionID()).send();
            String username = (String)res.get("username");
            Platform.runLater(() -> userNameLabel.setText(username));
            setupListView();
        }


    }

    @FXML
    private void onLogInHyperlinkClick(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader();
        Stage dialogStage = new Stage();
        Parent root = null;
        dialogStage.setTitle("Log in to sQuire");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(logInHyperlink.getScene().getWindow());
        dialogStage.setResizable(false);
        try
        {
            root = loader.load(getClass().getResource("/fxml/LogInDialog.fxml"));
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.setTitle("Log in to sQuire");
            dialogStage.showAndWait();
            Platform.runLater(() -> logInHyperlink.setVisible(false));
            Platform.runLater(() -> registerHyperlink.setVisible(false));
            Platform.runLater(() -> userNameLabel.setText(userName));
            if(Main.getSessionID() != null)
            {
                setupListView();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML private void onRegisterHyperlinkClick(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader();
        Stage dialogStage = new Stage();
        Parent root = null;
        dialogStage.setTitle("Register for sQuire");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(registerHyperlink.getScene().getWindow());
        dialogStage.setResizable(false);
        try
        {
            root = loader.load(getClass().getResource("/fxml/RegisterDialog.fxml"));
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void onNewProjectHyperlinkClick(ActionEvent event)
    {
        if (Main.getSessionID() == null)
        {
            onLogInHyperlinkClick(new ActionEvent());
            return;
        }
        if (event.getSource() == newProjectHyperlink)
        {
            FXMLLoader loader = new FXMLLoader();
            Stage stage = (Stage) newProjectHyperlink.getScene().getWindow();
            stage.setResizable(false);
            try
            {
               // Parent root = loader.load(getClass().getResource("/fxml/NewProject.fxml"));
                Parent root = loader.load(getClass().getResource("/fxml/NewProject2.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("New Project");
                stage.setHeight(460);
                stage.setWidth(610);
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
//    private void onOpenProjectHyperlinkClick(ActionEvent event)
//    {
//        if (event.getSource() == openProjectHyperlink)
//        {
//            Stage stage = null;
//            stage = (Stage) openProjectHyperlink.getScene().getWindow();
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            directoryChooser.setInitialDirectory(new File(Main.getProjectsDir()));
//            directoryChooser.setTitle("Choose Project Location");
//            File selectedDir = directoryChooser.showDialog(stage);
//            if (selectedDir != null)
//            {
//
//                System.out.println(selectedDir);
//            }
//        }
//    }
    @FXML
    private void onBrowseProjectsHyperlinkClick(ActionEvent event)
    {
        if (event.getSource() == browseProjectsHyperlink)
        {
            FXMLLoader loader = new FXMLLoader();
            Stage stage = (Stage) browseProjectsHyperlink.getScene().getWindow();
            try
            {
                Parent root = loader.load(getClass().getResource("/fxml/ProjectBrowsing.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Browse Projects");
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML private void onSettingsHyperlinkClick(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader();
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(logInHyperlink.getScene().getWindow());
        dialogStage.setResizable(false);
        try
        {
            Parent root = loader.load(getClass().getResource("/fxml/SettingsDialog.fxml"));
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.setTitle("Settings");
            dialogStage.showAndWait();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML private void onAvatarImageViewClick()
    {
        if (Main.userNotLoggedIn.getValue())
        {
            onLogInHyperlinkClick(new ActionEvent());
        }
        else
        {
            FXMLLoader loader = new FXMLLoader();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("User Profile");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(avatarImageView.getScene().getWindow());
            dialogStage.setResizable(false);
            try
            {
                Parent root = loader.load(getClass().getResource("/fxml/Preferences2.fxml"));
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    // Methods to instantiate the project list



//    public static void getProjectsWithUid()
//    {
//        if (!userName.isEmpty())
//        {
//
//            Response res = new Request("project/getProjectsWithUid").set("sessionID", Main.getSessionID()).send();
//            String username = (String)res.get("username");
//        }
//    }



    public void setupListView()
    {
        ObservableList observableList = FXCollections.observableArrayList();
        //Set up tree view cell factory
        // TODO: change next line to get x number of recent projects from DB. Will likely want to do this for open as
        // well
        Response res = new Request("project/getUserRecentProjects")
                .set("sessionID", Main.getSessionID())
                .send();
        ArrayList<ProjectData> projData = (ArrayList<ProjectData>) res.get("projects");
        observableList.setAll(projData);


//        recentProjectsListView.setItems(projData);
//
//        fileExplorer.setRoot(rootItem);
//        fileExplorer.setEditable(false);


        recentProjectsListView.setCellFactory(list -> new RecentListViewCell()
        );

        recentProjectsListView.setItems(observableList);
        // One way to get the clicked on cell

        recentProjectsListView.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if (mouseEvent.getClickCount() == 2)
                {
                    ProjectData selectedItem = (ProjectData) recentProjectsListView.getSelectionModel()
                            .getSelectedItem();
                    if (selectedItem != null)
                    {
                        Main.projectID = selectedItem.projectUUID;
                        loadEditorScene();
                    }
                }
            }

        });
    }

    public void loadEditorScene() {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = (Stage) recentProjectsListView.getScene().getWindow();
        //stage.setResizable(false);
        try {
            Parent root = loader.load(getClass().getResource("/fxml/Editor.fxml"));
            Scene scene = new Scene(root);
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