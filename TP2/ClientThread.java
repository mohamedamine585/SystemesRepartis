package TP2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class ClientThread {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("localhost", 9876);
            System.out.println("Connected to server.");

            // Input and output streams for communication with the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Send a message to the server
            String messageToSend = "Hello from client!";
            writer.println(messageToSend);

            // Receive reversed message from the server
            String reversedMessage = reader.readLine();
            System.out.println("Server reversed message: " + reversedMessage);

            // Close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
