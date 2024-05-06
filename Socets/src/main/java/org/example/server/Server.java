// Resources
// https://www.baeldung.com/a-guide-to-java-sockets
// https://www.geeksforgeeks.org/socket-programming-in-java/
// https://www.baeldung.com/java-serialization

package org.example.server;
import org.example.protcol.Protocol;

import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
public class Server {
    public static void main(String[]args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is running and waiting");

            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection with client established");

                ServerThread serverThread = new ServerThread(clientSocket);
                Thread thread = new Thread(serverThread);
                thread.start();

            }
        }
        catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }

    }
}
