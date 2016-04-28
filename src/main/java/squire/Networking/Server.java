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
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                System.out.println(in.readLine());
                out.println("got it" + i);
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex){
                    System.exit(0);
                }
                clientSocket.close();
            } catch (IOException e) {
                //some error with the socket session, ignore and continue
                e.printStackTrace();
            }
        }
    }
}
