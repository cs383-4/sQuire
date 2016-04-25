import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.io.IOException;
import java.io.InputStream;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Created by Domn Werner on 4/25/2016.
 */
public class HomeTest extends ApplicationTest
{
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
        Parent root = loader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("sQuire Home");
        stage.setHeight(400);
        stage.setWidth(600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Copy/paste this test case and replace 'Element' in the function name with
     * the UI element being tested and 'elementNameHere' in the body of the function
     * with the fx:id assigned to the testee element in the .fxml file.
     * @throws Exception
     */
//    @Test
//    public void verifyElementLoaded() throws Exception
//    {
//        verifyThat("#elementNameHere", NodeMatchers.isNotNull());
//    }

    @Test
    public void verifyRecentProjectsListViewLoaded() throws Exception
    {
        verifyThat("#recentProjectsListView", NodeMatchers.isNotNull());
    }

    @Test
    public void verifyAvatarImageViewLoaded() throws Exception
    {
        verifyThat("#avatarImageView", NodeMatchers.isNotNull());
    }

    @Test
    public void verifyNewProjectHyperlinkLoaded() throws Exception
    {
        verifyThat("#newProjectHyperlink", NodeMatchers.isNotNull());
    }

    @Test
    public void verifyOpenProjectHyperlinkLoaded() throws Exception
    {
        verifyThat("#openProjectHyperlink", NodeMatchers.isNotNull());
    }

    @Test
    public void verifyRegisterHyperlinkLoaded() throws Exception
    {
        verifyThat("#registerHyperlink", NodeMatchers.isNotNull());
    }

    @Test
    public void verifyLogInHyperlinkLoaded() throws Exception
    {
        verifyThat("#logInHyperlink", NodeMatchers.isNotNull());
    }

    @Test
    public void verifySettingsHyperlinkLoaded() throws Exception
    {
        verifyThat("#settingsHyperlink", NodeMatchers.isNotNull());
    }
}
