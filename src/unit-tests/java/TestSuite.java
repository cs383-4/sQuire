import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import squire.controllers.EditorControllerTest;
import google.mobwrite.MobWriteClientTest;

/**
 * Created by Domn Werner on 4/23/2016.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({EditorControllerTest.class, MobWriteClientTest.class, ProjectDatabaseTest.class, HomeTest.class}) // Add your test classes here.
public class TestSuite
{

}
