package squire.controllers;

import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import junit.framework.TestCase;
import org.fxmisc.richtext.CodeArea;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import squire.Main;
import google.mobwrite.ShareJTextComponent;
import com.sun.scenario.Settings;
import google.mobwrite.ShareJTextComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import squire.FileList;
import squire.Main;
import squire.Projects.Project;
import squire.Users.User;

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

           ec.setupMobWrite(ca, test);

       }
    }

//    @Test
//    public void testCompileCode() throws Exception
//    {
//
//    }
}