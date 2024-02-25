package TP1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUdp {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter a string to be sent to the server (type 'exit' to quit):");
                String userInput = scanner.nextLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Client is closing.");
                    break;
                }

                // Send the user input to the server
                byte[] sendData = userInput.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                // Receive and print the reversed string from the server
                byte[] receivedData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
                clientSocket.receive(receivePacket);

                String reversedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server Response: " + reversedString);
            }

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
