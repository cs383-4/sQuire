package squire.Users;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Project testproject = new Project();

        testproject.save();

    }

    @Test
    public void testGetSetOwner() throws Exception
    {
        Project testproject = new Project();
        testproject.setOwner(new User("testowner","test@email.com","testpassword"));

        testproject.save();

        assertEquals("testowner", testproject.getOwner().getUsername());
    }


}