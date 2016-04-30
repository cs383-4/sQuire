import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import squire.Users.Project;
import squire.Users.User;
import squire.controllers.NewProjectController3;

import java.io.File;
import java.util.ArrayList;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Created by Matthew Daniel on 4/26/2016.
 */
public class EditorTest extends ApplicationTest
{
    //@Rule
   // public JavaFxThreadingRule threadingRule = new JavaFxThreadingRule();

    // The parent node of the scene.
    Node rootNode;

    //A list of the names of the nodes to test.
    private static ArrayList<String> nodeNamesToTest = new ArrayList<String>();

    private String projectName;
    private String projectLocation;
    private String projectDescription;
    private Project createdProject;
    private User currentUser;
    private NewProjectController3 npc;

    private Project p;


    /**
     * Add the name of nodes you want to test here.
     */
    @Before
    public void setUpNodesToTest()
    {
        currentUser = new User("test", "test");
        npc = new NewProjectController3();
        File dummyFile = new File(System.getProperty("user.dir") + "src/main/resources/Test_Files");

        p = new Project("test", currentUser,"test","test",dummyFile);

        currentUser.setCurrentProject(p);

        nodeNamesToTest.add("#avatarImageView");
        nodeNamesToTest.add("#homeButton");
        nodeNamesToTest.add("#fileExplorer");
        nodeNamesToTest.add("#editorTextArea");
        nodeNamesToTest.add("#saveButton");
        nodeNamesToTest.add("#compilationOutputTextArea");
        nodeNamesToTest.add("#sourceCodeTextArea");
        nodeNamesToTest.add("#editorTabPane");

        Platform.setImplicitExit(true);

        // Added a bogus name to prove that it catches failures.
       // nodeNamesToTest.add("#sampleFail");
    }

    @After
    public void close() throws Exception
    {
        this.stop();
    }

    /**
     * Starts the GUI application for testing.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception
    {
//        String name = "test";
//        String description = "";
//        String testLocation = System.getProperty("user.dir") + "/src/gui-tests/java/TestDir";
//        String fileLocation = npc.initProjectFields(name, description, testLocation);

       // npc.copyMainFile(fileLocation);


        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/fxml/Editor.fxml"));
        Scene scene = new Scene(root);
     //   stage.setTitle("sQuire Editor - Project " + currentUser.getCurrentProject().toString());
        stage.setScene(scene);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        int fromEdge = 50;

        //set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX() + fromEdge/2);
        stage.setY(primaryScreenBounds.getMinY() + fromEdge/2);
        stage.setWidth(primaryScreenBounds.getWidth() - fromEdge);
        stage.setHeight(primaryScreenBounds.getHeight() - fromEdge);
        stage.setResizable(true);
        stage.show();

    }



    /**
     * Verifies that all of the important scene nodes have loaded properly
     * by asserting that they are not null.
     * @throws Exception
     */
    @Test
    public void verifyUiElementsLoaded() throws Exception
    {
        verifyThat(rootNode, NodeMatchers.isNotNull());

        for (String nodeName : nodeNamesToTest)
        {
            verifyThat(nodeName, NodeMatchers.isNotNull());
        }
    }
}