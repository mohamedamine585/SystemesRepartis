package TP1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTcp {
    static final int maxSize = 3;
    static int clientsNB = 0;
    static Socket[] clients = new Socket[maxSize];

    public static void main(String[] args) {
        try {
            // Create a server socket on port 8080
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server TCP is listening on localhost:8080");

            // Accept incoming connections until the maximum number of clients is reached
            while (clientsNB < maxSize) {
                // Accept a client connection
                Socket connectionSocket = serverSocket.accept();

                // Store the client socket and increment the client counter
                clients[clientsNB] = connectionSocket;
                clientsNB++;

                // Start a new thread to handle the client
                new Thread(() -> handleClient(connectionSocket)).start();
            }

            // Close the server socket outside the loop
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to handle each client in a separate thread
    private static void handleClient(Socket clientSocket) {
        try  {
            // Create input and output streams for the client
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        
            // Read the message from the client
            String clientSentence = inFromClient.readLine();
            System.out.println("Received from client: " + clientSentence);

            if (clientsNB < maxSize) {
                // If there is space for a new client, store the client's socket and increment the counter
                clients[clientsNB] = clientSocket;
                clientsNB++;

                // Iterate through stored clients and send the reversed message to each one
                for (int i = 0; i < clientsNB; i++) {
                    Socket currentClient = clients[i];
                    if (currentClient != null) {
                        // Reverse the received message and send it back to the client
                        String reversedMessage = new StringBuilder(clientSentence).reverse().toString();
                        PrintWriter outToCurrentClient = new PrintWriter(currentClient.getOutputStream(), true);
                        outToCurrentClient.println("Reversed: " + reversedMessage);
                    }
                }
            } else {
                // If the maximum number of clients is reached, close the connection
                 outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                outToClient.println("close connection");
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
