package TP4;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) {
        try {
            // Create a voiture object
            Voiture myVoiture = new Voiture("Sedan", "Toyota");

            // Set carburant for myVoiture
            myVoiture.setCarburant(30); // You can set any desired value

            // Serialize myVoiture object
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objOutputStream = new ObjectOutputStream(byteStream);
            objOutputStream.writeObject(myVoiture);

            // Get the serialized bytes
            byte[] sendData = byteStream.toByteArray();

            // Send the serialized object to the server
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            System.out.println("Sent Voiture");

            // Close the socket
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
