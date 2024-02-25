package TP1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUdp {
    static int maxSize = 3;
    static int clientsNB = 0;

    static DatagramPacket[] clients = new DatagramPacket[maxSize];

    public static void main(String[] args) {
        try {
            // Create a DatagramSocket to listen on port 9876
            DatagramSocket serverSocket = new DatagramSocket(9876);
            System.out.println("Server UDP is running on localhost:" + serverSocket.getLocalPort());

            while (true) {
                // Create a buffer to receive data from clients
                byte[] receivedData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);

                // Receive data from a client
                serverSocket.receive(receivePacket);

                // Convert received data to a string
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + receivedMessage);

                if (clientsNB < maxSize) {
                    // If there is space for a new client, store the client's packet and increment the counter
                    clients[clientsNB] = receivePacket;
                    clientsNB++;

                    // Iterate through stored clients and send the reversed message to each one
                    for (int i = 0; i < clientsNB; i++) {
                        DatagramPacket client = clients[i];
                        if (client != null) {
                            // Reverse the received message and send it back to the client
                            String reversedMessage = new StringBuilder(receivedMessage).reverse().toString();
                            serverSocket.send(new DatagramPacket(reversedMessage.getBytes(),
                                    reversedMessage.getBytes().length, client.getAddress(), client.getPort()));
                        }
                    }
                } else {
                    // If the maximum number of clients is reached, send a "close connection" message to each client
                    for (int i = 0; i < clientsNB; i++) {
                        DatagramPacket client = clients[i];
                        if (client != null) {
                            String message = "close connection";
                            serverSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length,
                                    client.getAddress(), client.getPort()));
                        }
                    }
                    // Break out of the loop to close the server
                    break;
                }
            }

            // Close the server socket
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
