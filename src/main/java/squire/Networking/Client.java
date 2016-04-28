package squire.Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("hi");
            System.out.println(in.readLine());
            socket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static public void main(String[] args) {
        while(true) {
            request();
        }
    }
}
