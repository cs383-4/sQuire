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
import squire.chatserver.ChatClient;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by MattDaniel on 3/31/16.
 */
public class ChatController implements Initializable
{
    private static Socket clientSocket = null;
    // The output stream
    private static PrintStream os = null;
    // The input stream
    private static DataInputStream is = null;

    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    public void initialize(URL location, ResourceBundle resources)
    {
        // The default port.
        int portNumber = 2222;
        // The default host.
        String host = "squireserver.westus.cloudapp.azure.com";


    /*
     * Open a socket on a given host and port. Open input and output streams.
     */
        try {
            clientSocket = new Socket(host, portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host "
                    + host);
        }


    }

    /*
     * Create a thread to read from the server. (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */



    @FXML TextArea chatArea;
    @FXML TextField enterTextField;
    @FXML Button sendButton;

    private void onSendButtonClick(ActionEvent n){
    /*
     * If everything has been initialized then we want to write some data to the
     * socket we have opened a connection to on the port portNumber.
     */
        if (clientSocket != null && os != null && is != null) {
            try {

        /* Create a thread to read from the server. */
                new Thread(new ChatClient()).start();
                os.println(enterTextField.getText());
        /*
         * Close the output stream, close the input stream, close the socket.
         */
                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }


    private void WriteText() {
    /*
     * Keep on reading from the socket till we receive "Bye" from the
     * server. Once we received that then we want to break.
     */
        String responseLine;
        try {
            while ((responseLine = is.readLine()) != null) {
                chatArea.appendText(responseLine + "\n");
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}