import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import squire.Users.PasswordHashTest;
import squire.Users.UserTest;
import squire.Users.ProjectDatabaseTest;
import squire.controllers.EditorControllerTest;
import google.mobwrite.MobWriteClientTest;
import squire.controllers.NewProjectController3Test;

/**
 * Created by Domn Werner on 4/23/2016.
 */

//TODO: We need to modify this to run the JavaFX tests in separate suites I think.
    // Not sure how...
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EditorControllerTest.class,
        NewProjectController3Test.class,
        HomeTest.class,
        NewProjectTest.class,
        MobWriteClientTest.class,
        EditorTest.class,
        ProjectDatabaseTest.class,
        UserTest.class,
        PasswordHashTest.class,
}) // Add your test classes here.
public class TestSuite
{

}
