import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import squire.Users.PasswordHashTest;
import squire.Users.ProjectDatabaseTest;
import squire.controllers.EditorControllerTest;
import google.mobwrite.MobWriteClientTest;

/**
 * Created by Domn Werner on 4/23/2016.
 */

//TODO: We need to modify this to run the JavaFX tests in separate suites I think.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EditorControllerTest.class,
        MobWriteClientTest.class,
        ProjectDatabaseTest.class,
        PasswordHashTest.class,
        HomeTest.class,
        NewProjectTest.class,
        EditorTest.class,
}) // Add your test classes here.
public class TestSuite
{

}
