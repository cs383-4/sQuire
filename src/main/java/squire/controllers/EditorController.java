package squire.controllers;

import com.avaje.ebean.event.changelog.ChangeLogListener;
import com.avaje.ebeaninternal.server.lib.util.Str;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import squire.FileList;


import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by MattDaniel on 3/31/16.
 */
public class EditorController implements Initializable
{
    @FXML private ImageView avatarImageView;
    @FXML private Button homeButton;
    @FXML private TreeView fileExplorer;
    @FXML private TextArea editorTextArea;

    FileList fl = new FileList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        avatarImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onAvatarImageViewClick());



    }


    private void onAvatarImageViewClick()
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

    @FXML private void onHomeButtonClick(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/fxml/Home.fxml"));
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setHeight(400);
            stage.setWidth(600);
            stage.setTitle("sQuire Home");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void setupFileList(FileList fileList)
    {
        fl.copy(fileList);
        fl.print();

        //Set up tree view cell factory

        TreeItem<String> rootItem = new TreeItem<>(fl.getProjectName());
        rootItem.setExpanded(true);

        for (String file: fl.getFileList())
        {
            // Get just the filename
            file = file.substring(file.lastIndexOf("/")+1);
            TreeItem<String> item = new TreeItem<>(file);
            rootItem.getChildren().add(item);
        }

        fileExplorer.setRoot(rootItem);
        fileExplorer.setEditable(true);
        fileExplorer.setCellFactory(p -> {
                // Name used for class in oracle online demo
            return new TextFieldTreeCellImpl();
        }
        );



        // One way to get the clicked on cell
        fileExplorer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                try {
                    Scanner input = new Scanner(System.in);
                    String filePath = fl.getMatchingFile(selectedItem.getValue());
                    File file = new File(filePath);
                    input = new Scanner(file);


                    while (input.hasNextLine()) {
                        String line = input.nextLine();
                        editorTextArea.appendText(line + "\n");
                    }
                    input.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });


    }




    // Using example at http://docs.oracle.com/javafx/2/ui_controls/tree-view.htm
    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;

        public TextFieldTreeCellImpl()
        {

        }

//TODO: See if we can work with TreeCell objects of FileList items? Instead of strings
        @Override
        public void startEdit()
        {
            super.startEdit();

            if (textField == null)
            {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit()
        {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(String item, boolean empty)
        {
            super.updateItem(item, empty);

            if (empty)
            {
                setText(null);
                setGraphic(null);
            }
            else
            {
                if (isEditing())
                {
                    if (textField != null)
                    {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                }
                else
                {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                }
            }
        }

        private void createTextField()
        {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>()
            {

                @Override
                public void handle(KeyEvent t)
                {
                    if (t.getCode() == KeyCode.ENTER)
                    {
                        commitEdit(textField.getText());
                    }
                    else if (t.getCode() == KeyCode.ESCAPE)
                    {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString()
        {
            return getItem() == null ? "" : getItem().toString();
        }
    }


}
