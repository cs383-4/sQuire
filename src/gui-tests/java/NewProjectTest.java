import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.util.ArrayList;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Created by Matthew Daniel on 4/26/2016.
 */
public class NewProjectTest extends ApplicationTest
{
    // The parent node of the scene.
    Node rootNode;

    //A list of the names of the nodes to test.
    private static ArrayList<String> nodeNamesToTest = new ArrayList<String>();

    /**
     * Add the name of nodes you want to test here.
     */
    @Before
    public void setUpNodesToTest()
    {

        nodeNamesToTest.add("#projectTitleTextField");
        nodeNamesToTest.add("#projectDescriptionTextArea");
        nodeNamesToTest.add("#locationTextField");
        nodeNamesToTest.add("#browseButton");
        nodeNamesToTest.add("#finishButton");
        nodeNamesToTest.add("#cancelButton");
        nodeNamesToTest.add("#backButton");

        // Added a bogus name to prove that it catches failures.
        //nodeNamesToTest.add("#sampleFail");

        Platform.setImplicitExit(true);
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
        // This is returning null, thus the catch block is executing.
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/fxml/NewProject2.fxml"));
        rootNode = root;
        Scene scene = new Scene(root);
        stage.setTitle("New Project");
        stage.setHeight(460);
        stage.setWidth(610);
        stage.setResizable(false);
        stage.setScene(scene);
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