package org.example.server;

import org.example.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket clientSocket;

    public static final String ReadyState = "Ready";
    public static final String ReadyForMessagesState = "Ready for messages";
    public static final String FinishedState = "Finished";

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

            outputStream.writeObject(ReadyState);

            Integer receivedNumber = (Integer) inputStream.readObject();
            System.out.println("Received number " + receivedNumber);

            outputStream.writeObject(ReadyForMessagesState);

            for(int i = 0; i < receivedNumber; i++) {
                Message receivedMessage = (Message) inputStream.readObject();
                System.out.println("Received message nr " + i + " : " + receivedMessage.getContent());
            }

            outputStream.writeObject(FinishedState);

        }
        //IOException
        catch(Throwable e) {
            System.out.println("Server thread stopped");
        }
    }
}
