package squire.Users;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.util.ArrayList;

public class ProjectDatabaseTest extends TestCase
{
    /**
     * This method runs before the tests start.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {

    }

    /**
     * This method is for cleaning up. It runs after tests are complete.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void testTrueBaseline() throws Exception
    {
        assertEquals(1,1);
    }

    @Test
    public void testAddProject() throws Exception
    {
        User testuser = new User("1","2","3");
        File testFile = new File("");

        Project testproject = new Project("name", testuser,"3","4",testFile);

        testproject.save();

    }

    @Test
    public void testGetSetOwner() throws Exception
    {
        User testuser = new User("4","5","6");
        File testFile = new File("");
        Project testproject = new Project("name", testuser,"3","4",testFile);
        testproject.setProjectOwner(new User("testowner","email@email.com","testpassword"));

        testproject.save();

        assertEquals("testowner", testproject.getProjectOwner().getUsername());
    }


}