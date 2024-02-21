package TP1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ServerUdp {
    static int maxSize = 3;
    static int clientsNB = 0;

    static DatagramPacket[] clients = new DatagramPacket[maxSize];

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            System.out.println("Server UDP is running on localhost :" + serverSocket.getLocalPort());

            while (true) {
                byte[] receivedData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);

                serverSocket.receive(receivePacket); 

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + receivedMessage);
                if (clientsNB < maxSize) {
                    for (DatagramPacket client : clients) {
                        if (client != null) {
                            String message = "Hello from Client" + (clientsNB + 1);
                            serverSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length,
                                    client.getAddress(), client.getPort()));
                        }
                    }

                    clients[clientsNB] = receivePacket;
                    clientsNB++;


                } else {
                    for (DatagramPacket client : clients) {
                        if (client != null) {
                            String message = "close connection";
                            serverSocket.send(new DatagramPacket(message.getBytes(), message.getBytes().length,
                                    client.getAddress(), client.getPort()));
                        }
                    }
                    break;
                }
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
