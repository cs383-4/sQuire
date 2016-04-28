package squire.Networking;

import java.io.*;
import java.net.Socket;

/**
 * Created by brandon on 4/27/16.
 */
public class Client {
    static final String address = "localhost";
    static final int port = 3017;

    static void request() {
        try {
            Socket socket = new Socket(address, port);
            //each end needs the output stream to exist before creating the input stream, so do that, and then flush
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream((socket.getInputStream()));
            try {
                out.writeObject("hi");
                System.out.println(in.readObject());
            } catch (ClassNotFoundException ex) {
                //something wrong with reading the object
                ex.printStackTrace();
            }
            socket.close();
        } catch (IOException ex) {
            //something wrong with the socket
            ex.printStackTrace();
        }
    }

    static public void main(String[] args) {
        while(true) {
            request();
        }
    }
}
