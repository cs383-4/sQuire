package squire.Networking;

import squire.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The client contains the networking logic to communicate with the server
 * the Client.send function sends a request to the server, waits for the response, and returns it.
 *
 * Note: the proper way to call this function is from Request.send()
 */
class Client {
    private static final int port = 3017;

    /**
     * Sends the given request to the server
     * @param req the request to send to the server
     * @return the response from the server
     */
    static Response send(Request req) {
        Response res = null;

        try {
            Socket socket = new Socket(Main.serverURL, port);
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
            System.out.println("Error connecting to " + Main.serverURL + ":" + port);
            System.out.println("Is the server running (SquireServer)?");
        }
        return res;
    }

    public static void main(String[] args) {
        Request req = new Request("test");
        System.out.println(req.send().get("test"));
    }
}
