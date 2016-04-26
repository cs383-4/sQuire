package squire.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by MattDaniel on 4/25/16.
 */
public class NewProjectController3Test
{

    private NewProjectController3 npc;

    @Before
    public void setUp() throws Exception
    {
        npc = new NewProjectController3();
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void testInitialize() throws Exception
    {

    }


    //Fails if we enter an empty string
    @Test
    public void testInitProjectFields() throws Exception
    {
        String [] testNames = {"", ";", "Space Test", "ALLCAPS"};
        String [] testDescriptions = {"", ";", "Space Test", "ALLCAPS"};
        String location = System.getProperty("user.dir") + "/src/unit-tests/java/squire/controllers";
        String testLocationRoot = location + "/ProjectLocationTest";
        String fileLoc = "";

        String testLocation = "";

        for (String name: testNames)
        {

            for (String description: testDescriptions)
            {
                testLocation = testLocationRoot + File.separator + name;
                fileLoc = npc.initProjectFields(name, description, testLocation);
                File fileFromLoc = new File(fileLoc);

                // For all combinations of names and descriptions, see if a new project directory was created in the
                // parent directory
                assertTrue("No file location!: " + testNames, fileFromLoc.getParentFile().getParent().equals
                        (testLocationRoot));

//                System.out.println(fileFromLoc.getParentFile().getParent());
//                System.out.println();
            }
        }


    }

    @Test
    public void testCopyMainFile() throws Exception
    {

        String filepath = (System.getProperty("user.dir") +
                "/src/unit-tests/java/squire/controllers/ProjectLocationTest");
        File fileAtPath = new File(filepath);
        fileAtPath.mkdir();
        filepath += "/CopyTestProject";
        File fileAtPath2 = new File(filepath);
        fileAtPath2.mkdir();
        filepath += "/Main.java";
        npc.copyMainFile(filepath);

        File testFile = new File(filepath);

        assertTrue("No file!: " + filepath, fileAtPath.exists());

        npc.copyMainFile(filepath);

        assertTrue("No file!: " + filepath, fileAtPath.exists());

    }
    
}