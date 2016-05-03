package squire.controllers;

import google.mobwrite.ShareJTextComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import squire.Main;
import squire.Users.Project;
import squire.Users.PropertiesController;
import squire.Users.User;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by MattDaniel on 3/31/16.
 */
public class ChatController implements Initializable
{
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML TextArea chatArea;
    @FXML TextField enterTextField;


    public void writeToChatArea(String s)
    {

    }


}