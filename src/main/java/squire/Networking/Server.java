package squire.Networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by brandon on 4/27/16.
 */


public class Server {
    static final int portNumber = 3017;
    static int i = 0;

    static public void main(String args[]) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            //ServerSocket could not be created, quit the server
            e.printStackTrace();
            System.exit(1);
        }

        while(true) {
            try {
                Socket clientSocket = serverSocket.accept();
                //each end needs the output stream to exist before creating the input stream, so do that, and then flush
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object a = in.readObject();
                    System.out.println(a.getClass().getName());
                    out.writeObject("got i" + i);
                    i++;
                } catch (ClassNotFoundException ex) {
                    //something wrong with reading the object
                    ex.printStackTrace();
                }
                clientSocket.close();
            } catch (IOException e) {
                //some error with the socket session, ignore and continue
                e.printStackTrace();
            }
        }
    }
}
