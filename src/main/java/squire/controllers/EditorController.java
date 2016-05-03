package squire.controllers;

import google.mobwrite.ShareJTextComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import squire.Main;
import squire.Networking.Request;
import squire.Networking.Response;
import squire.Users.Project;
import squire.Users.PropertiesController;
import squire.Users.User;


import java.io.*;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.fxmisc.richtext.*;

/**
 * Created by MattDaniel on 3/31/16.
 */
public class EditorController implements Initializable {
    @FXML
    private ImageView avatarImageView;
    @FXML
    private Button homeButton;
    @FXML
    private TreeView fileExplorer;
    @FXML
    private TextArea editorTextArea;

    @FXML
    private Button saveButton;

    // Compilation vars.
    @FXML
    private TextArea compilationOutputTextArea;
    @FXML
    private CodeArea sourceCodeTextArea;
    @FXML
    private TabPane editorTabPane;

    private File oldFile;
    private ArrayList<Tab> openTabs = new ArrayList<>();

    private CodeArea currentCodeArea;

    private PropertiesController pc = null;
    private String projectPath = "";


    // Compilation vars.

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avatarImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onAvatarImageViewClick());
        compilationOutputTextArea.setWrapText(true);
        //setupFileList();
        loadProject();
        pc = PropertiesController.getPropertiesController();
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

    //load the project set in Main.projectID
    public void loadProject() {
        projectPath = Main.getProjectsDir() + File.separator + Main.getProjectName();
        setupFileList();
    }

    public void setupFileList() {
        //Set up tree view cell factory
        TreeItem<String> rootItem = new TreeItem<>(Main.getProjectName());
        rootItem.setExpanded(true);

        //TODO:make this not hard coded
        /*
        ArrayList<File> fileList = new ArrayList<>();
        for (File file : fileList) {
            // Get just the filename
            String fileName = file.getName();
            TreeItem<String> item = new TreeItem<>(fileName);
            rootItem.getChildren().add(item);
        }
        */
        rootItem.getChildren().add(new TreeItem<>("Main.java"));

        fileExplorer.setRoot(rootItem);
        fileExplorer.setEditable(false);
        fileExplorer.setCellFactory(p -> {
            // Name used for class in oracle online demo
            return new TextFieldTreeCellImpl();
        });

        // One way to get the clicked on cell
        fileExplorer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    TreeItem<String> selectedItem = (TreeItem<String>) fileExplorer.getSelectionModel().getSelectedItem();

                    try {
                        // TODO: get this string to be the actual path of the file
                        //String newFilePath = currentProject.getProjectPath() + File.separator + selectedItem.getValue();

                        CodeArea newTabCodeArea = new CodeArea();

                        createNewTab(selectedItem.getValue(), newTabCodeArea);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }


    //Create new tab programatically
    public void createNewTab(String name, CodeArea newTabCodeArea) {
        Boolean isInList = false;
        Tab newTab = new Tab();
        newTab.setText(name);

        for (Tab t : editorTabPane.getTabs()) {
            if (t.getText().equals(newTab.getText())) {

                isInList = true;
                switchToTab(t);
            }

        }
        if (isInList == false) {
            newTabCodeArea.setLayoutX(167.0);
            newTabCodeArea.setLayoutY(27.0);
            newTabCodeArea.setPrefHeight(558.0);
            newTabCodeArea.setPrefWidth(709.0);

            setupMobWrite(newTabCodeArea, Main.getProjectID() + ":" + name);
            AnchorPane ap = new AnchorPane(newTabCodeArea);


            //Setup information from FXML
            //                    <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="180.0" prefWidth="200.0">
            //                    <children>
            //                    <CodeArea fx:id="sourceCodeTextArea" layoutX="167.0" layoutY="27.0" prefHeight="558.0" prefWidth="709.0" AnchorPane.bottomAnchor="0.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="0.0" />
            //                    </children>
            //                    </AnchorPane>

            ap.setMinHeight(0.0);
            ap.setMinWidth(0.0);
            ap.setPrefHeight(180.0);
            ap.setPrefWidth(200.0);


            ap.setBottomAnchor(newTabCodeArea, 0.0);
            ap.setTopAnchor(newTabCodeArea, 0.0);
            ap.setRightAnchor(newTabCodeArea, 0.0);
            ap.setLeftAnchor(newTabCodeArea, 0.0);

            newTab.setContent(ap);
            editorTabPane.getTabs().add(newTab);
        }
        //TODO: Fix this!
        currentCodeArea = newTabCodeArea;
    }


    private void switchToTab(Tab t) {
        editorTabPane.getSelectionModel().select(t);
    }

    // Write the file to the CodeArea
    public void writeFile(Scanner input, CodeArea newTabCodeArea) {
        String fullText = "";
        // Open the file on click
        newTabCodeArea.positionCaret(0);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            fullText += (line + "\n");
        }

        //TODO: COMMENT THIS OUT FOR DEMO
        newTabCodeArea.replaceText(fullText);
        input.close();
    }

    public String getText(CodeArea ca) {
        return ca.getText();
    }


    @FXML
    private void onSaveButtonClick(ActionEvent event) {
        // Basic way to write files back
        String oldFilePath;
        Tab curTab = editorTabPane.getSelectionModel().getSelectedItem();
        oldFilePath = projectPath + File.separator + curTab.getText();
        //oldFile = new File (oldFilePath);
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(oldFilePath));
            bf.write(currentCodeArea.getText());
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //TODO: create a new mobwrite component based on projectID, fileID in database that we can connect to
    // TODO: every time we switch tabs
    public void setupMobWrite(CodeArea ca, String name) {
        //mobwrite ids have to start with a letter. Append a letter to the start to make sure. s for squire!
        ShareJTextComponent mobwriteComponent = new ShareJTextComponent(ca, "s" + name);
        Main.getMobwriteClient().share(mobwriteComponent);
    }

    // Using example at http://docs.oracle.com/javafx/2/ui_controls/tree-view.htm
    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;

        public TextFieldTreeCellImpl() {
        }

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

    @FXML
    private void onRunButtonClick(ActionEvent event) {
        compileCode();
    }

    private void compileCode() {
        compilationOutputTextArea.appendText("Compiling...\n");
        String javacPath = pc.getProp("jdkLocation") + File.separator + "javac";
        String javaExePath = pc.getProp("jdkLocation") + File.separator + "java";

        try
        {
            String entryPoint = "Main.java";
            ProcessBuilder compilation = new ProcessBuilder(javacPath, entryPoint);
            System.out.println(projectPath);
            compilation.directory(new File(projectPath));
            Process p = compilation.start();
            int errorCode = p.waitFor();
            System.out.println("p1 Error Code: " + errorCode);


            ProcessBuilder execution = new ProcessBuilder(javaExePath, entryPoint.replace(".java", ""));
            execution.directory(new File(projectPath));
            Process p2 = execution.start();
            p2.waitFor(5, TimeUnit.SECONDS);
            int errorCode2 = p2.exitValue();
            System.out.println("p2 Error Code: " + errorCode2);

            String line;
            BufferedReader input = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            while ((line = input.readLine()) != null)
            {
                compilationOutputTextArea.appendText(line);
                compilationOutputTextArea.appendText("\n");
            }
            input.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}