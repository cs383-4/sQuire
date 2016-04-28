package squire.Networking;

import java.io.*;
import java.net.Socket;

/**
 * Created by brandon on 4/27/16.
 */
public class Client {
    private static final String address = "localhost";
    private static final int port = 3017;

    static Response send(Request req) {
        Response res = null;

        try {
            Socket socket = new Socket(address, port);
            //each end needs the output stream to exist before creating the input stream, so do that, and then flush
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream((socket.getInputStream()));

            try {
                //send the request
                out.writeObject(req);
                //read the response
                res = (Response) in.readObject();
            } catch (ClassNotFoundException ex) {
                //something wrong with reading the object
                ex.printStackTrace();
            }
            socket.close();
        } catch (IOException ex) {
            //something wrong with the socket
            ex.printStackTrace();
        }
        return res;
    }
}
