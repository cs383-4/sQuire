package squire.controllers;

import javafx.stage.DirectoryChooser;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
    public void testSetupFileList() throws Exception
    {

    }

    @Test
    public void testSetupMobWrite() throws Exception
    {

    }

    @Test
    public void testCompileCode() throws Exception
    {
        File jdkPath = new File(System.getenv("JAVA_HOME"));
        assertEquals("I:\\Program Files (x86)\\Java\\jdk1.8.0_20\\bin", jdkPath.getAbsolutePath());
    }

}