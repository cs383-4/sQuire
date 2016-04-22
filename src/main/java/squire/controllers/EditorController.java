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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
import squire.Main;
import squire.Projects.JavaSourceFromString;
import squire.Projects.Project;
import squire.Users.User;
import sun.tools.jar.CommandLine;


import javax.tools.*;
import java.io.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

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
    private TextArea sourceCodeTextArea;
    private Project currentProject;
    private User currentUser;

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

    public void setupFileList() {


        //Set up tree view cell factory

        TreeItem<String> rootItem = new TreeItem<>(currentProject.getProjectName());
        rootItem.setExpanded(true);

        for (JavaSourceFromString file : currentProject.getFileList()) {
            // Get just the filename
            String fileName = file.getFileName();
            TreeItem<String> item = new TreeItem<>(fileName);
            rootItem.getChildren().add(item);
        }






        fileExplorer.setRoot(rootItem);
        fileExplorer.setEditable(false);
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

                    // TODO: get this string to be the actual path of the file
                    String filePath = currentProject.getProjectPath() + File.separator + selectedItem
                            .getValue();
                   System.out.println(filePath);
                    File file = new File(filePath);
                    input = new Scanner(file);


                    while (input.hasNextLine()) {
                        String line = input.nextLine();
                        sourceCodeTextArea.appendText(line + "\n");
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
        System.out.println(compileCode());

        try {
            Process p = Runtime.getRuntime().exec("java " + currentProject.getEntryPointClassName());

            new Thread(() -> {
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;

                try {
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String compileCode()
    {
        compilationOutputLabel.setText("Compiling...");
        String compilationResult = null;
        String entryPointName = "";

        if (compiler == null) {
            compiler = ToolProvider.getSystemJavaCompiler();
        }

        if (compiler != null) {
            String code = sourceCodeTextArea.getText();
            // Placeholder name.
            String sourceName = "Main.java";
            if (sourceName.toLowerCase().endsWith(".java")) {
                entryPointName = sourceName.substring(0, sourceName.length() - 5);
            }
            JavaSourceFromString javaString = new JavaSourceFromString(entryPointName, code);
            ArrayList<JavaSourceFromString> compilationFiles = new ArrayList<JavaSourceFromString>();
            compilationFiles.add(javaString);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream);

            JavaCompiler.CompilationTask task = compiler.getTask(outputWriter, null, null, null, null, compilationFiles);

            boolean success = task.call();

            compilationOutputLabel.setText(outputStream.toString().replaceAll("\t", "  "));
            compilationResult = "Compiled without errors: " + success;
            compilationOutputLabel.setText(compilationResult);
        }
        else
        {
            compilationOutputLabel.setText("Compilation failed.");
        }

        return compilationResult;
    }


}
