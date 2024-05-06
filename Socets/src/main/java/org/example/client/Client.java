package org.example.client;

import org.example.message.Message;
import org.example.server.ServerThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost", 8080);
            System.out.println("Connected");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            String receivedStatus = (String) inputStream.readObject();
            System.out.println("Received status: " + receivedStatus);

            Scanner scanner = new Scanner(System.in);

            int number = 0;
            if (Objects.equals(receivedStatus, "Ready")) {
                System.out.print("Enter an integer: ");
                number = scanner.nextInt();
                outputStream.writeObject(number);
            }

            receivedStatus = (String) inputStream.readObject();
            System.out.println("Received status: " + receivedStatus);

            scanner.nextLine();
            if (Objects.equals(receivedStatus, "Ready for messages")) {
                for(int i = 0; i < number; i++) {
                    System.out.print("Enter " + (i + 1) + " message:");
                    String content = scanner.nextLine();
                    Message message = new Message(content);
                    outputStream.writeObject(message);
                }
            }

            receivedStatus = (String) inputStream.readObject();
            System.out.println("Received status: " + receivedStatus);

            socket.close();
            scanner.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred: " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
