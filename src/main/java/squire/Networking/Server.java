package squire.Networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Contains the logic needed to relieve requests, route them to the correct function, and return the result.
 * It runs as a loop, listening on the port given by portNumber.
 */


public class Server {
    private static final int portNumber = 3017;
    private static Router router = new Router();

    static public void main(String args[]) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            //ServerSocket could not be created, quit the server
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Listening on port " + portNumber);
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                //each end needs the output stream to exist before creating the input stream, so do that, and then flush
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Request req = (Request) in.readObject();

                    out.writeObject(router.route(req));
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
