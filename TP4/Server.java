package TP4;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                serverSocket.receive(receivePacket);

                // Deserialize the received object
                ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objInputStream = new ObjectInputStream(byteStream);
                Voiture receivedVoiture = (Voiture) objInputStream.readObject();

                // Set carburant for the received voiture on the server side
                receivedVoiture.setCarburant(50); // You can set any desired value

                System.out.println("Received voiture: " + receivedVoiture);
                        
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

