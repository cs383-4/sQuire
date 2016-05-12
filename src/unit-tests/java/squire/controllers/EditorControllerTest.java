package squire.controllers;

import junit.framework.TestCase;
import org.fxmisc.richtext.CodeArea;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import squire.FileList;

/**
 * Created by Domn Werner on 4/23/2016.
 *
 * This is the unit test class for the EditorController class.
 * Create your own of these for the classes that you have worked on.
 */
public class EditorControllerTest extends TestCase
{
    /**
     * This method runs before the tests start.
     * @throws Exception
     */

    public CodeArea ca;
    public EditorController ec;

    @Before
    public void setUp() throws Exception
    {
//       ca = new CodeArea();
        ec = new EditorController();
    }

    /**
     * This method is for cleaning up. It runs after tests are complete.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception
    {

    }

//    @Test
//    public void testSetupFileList() throws Exception
//    {
//
//    }

    @Test
    public void testSetupMobWrite() throws Exception
    {
        // Test setting up mob write with various component names
        String [] testStrings = {"hello", "123", "", ";", " ",};

       for (String test: testStrings)
       {

           ec.setupMobWrite(ca, test, false);

       }
    }

//    @Test
//    public void testCompileCode() throws Exception
//    {
//
//    }
}