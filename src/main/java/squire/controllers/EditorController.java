package squire.controllers;

import google.mobwrite.ShareJTextComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import squire.FileList;
import squire.Main;
import squire.Projects.JavaSourceFromString;
import squire.Projects.Project;
import squire.Users.User;


import javax.tools.*;
import java.awt.*;
import java.io.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.fxmisc.richtext.*;
/**
 * Created by MattDaniel on 3/31/16.
 */
public class EditorController implements Initializable
{
    @FXML
    private ImageView avatarImageView;
    @FXML
    private Button homeButton;
    @FXML
    private TreeView fileExplorer;
    @FXML
    private TextArea editorTextArea;

    FileList fl = new FileList();

    // Compilation vars.
    @FXML
    private Label compilationOutputLabel;
    @FXML
    private CodeArea sourceCodeTextArea;
    private Project currentProject;
    private User currentUser;

    private ShareJTextComponent mobwriteComponent;

    PrintStream compilationOutputStream;
    private JavaCompiler compiler;
    private DiagnosticCollector<JavaFileObject> diagnostics;
    StandardJavaFileManager fileManager;
    Iterable<? extends JavaFileObject> compilationUnits;
    JavaCompiler.CompilationTask task;
    // Compilation vars.

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avatarImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onAvatarImageViewClick());
        currentUser = Main.getCurrentUser();
        currentProject = currentUser.getCurrentProject();
        compilationOutputLabel.setWrapText(true);
        setupFileList();
        setupMobWrite();
    }

    private void onAvatarImageViewClick() {
        FXMLLoader loader = new FXMLLoader();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("User Profile");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(avatarImageView.getScene().getWindow());
        dialogStage.setResizable(false);
        try {
            Parent root = loader.load(getClass().getResource("/fxml/Preferences2.fxml"));
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onHomeButtonClick(ActionEvent event) {
        try {
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setupFileList()
    {


        //Set up tree view cell factory

        TreeItem<String> rootItem = new TreeItem<>(currentProject.getProjectName());
        rootItem.setExpanded(true);

        for (File file : currentProject.getFileList()) {
            // Get just the filename
            String fileName = file.getName();
            TreeItem<String> item = new TreeItem<>(fileName);
            rootItem.getChildren().add(item);
        }


        fileExplorer.setRoot(rootItem);
        fileExplorer.setEditable(false);
        fileExplorer.setCellFactory(p -> {
                // Name used for class in oracle online demo
            return new TextFieldTreeCellImpl();
        });



        // One way to get the clicked on cell
        fileExplorer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                try {
                    Scanner input = new Scanner(System.in);

                    // TODO: get this string to be the actual path of the file
                    String filePath = currentProject.getProjectPath() + File.separator + selectedItem
                            .getValue();
                    System.out.println(filePath);
                    File file = new File(filePath);
                    input = new Scanner(file);

                    sourceCodeTextArea.positionCaret(0);
                    while (input.hasNextLine()) {
                        String line = input.nextLine();
                        sourceCodeTextArea.appendText(line + "\n");
                    }
                    input.close();

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }


    public void setupMobWrite() {
        mobwriteComponent = new ShareJTextComponent(sourceCodeTextArea, currentProject.getProjectName());
        Main.getMobwriteClient().share(mobwriteComponent);
    }


    // Using example at http://docs.oracle.com/javafx/2/ui_controls/tree-view.htm
    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;

        public TextFieldTreeCellImpl()
        {

        }

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






    @FXML
    private void onRunButtonClick(ActionEvent event) {
        compileCode();
    }

    private void compileCode()
    {
        compilationOutputLabel.setText("Compiling...");
        File entryPoint = currentProject.getEntryPointClassFile();

        try
        {
            Process p = Runtime.getRuntime().exec("javac " + entryPoint.getName());
            p.waitFor();
            Process p2 = Runtime.getRuntime().exec("java " + entryPoint.getName().replace(".java", ""));
            p2.waitFor();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
