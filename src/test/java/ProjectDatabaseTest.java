import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
    public void testOneEqOne() throws Exception
    {
        assertEquals(1,1);
    }

}