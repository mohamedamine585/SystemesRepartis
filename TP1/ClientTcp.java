package TP1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTcp {
    public static void main(String[] args) {
        try {
            // Create a socket to connect to the server on localhost and port 8080
            Socket clientSocket = new Socket("localhost", 8080);

            // Create input and output streams for communication with the server
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Prompt the user to enter a string
                System.out.println("Enter a string to be sent to the server (type 'exit' to quit):");
                String userInput = scanner.nextLine();

                // Send the user input to the server
                outToServer.println(userInput);

                // Receive and print the server's response
                String serverResponse = inFromServer.readLine();
                System.out.println("Server Response: " + serverResponse);

                // If the user entered 'exit', close the client
                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Client is closing.");
                    break;
                }
            }

            // Close the client socket
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
