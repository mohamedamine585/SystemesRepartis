package TP1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTcp {
    static int maxSize = 3;

    static ArrayList<Socket> clients = new ArrayList(maxSize);
     public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server TCP is listening on localhost:8080");
            while (true) {
                Socket connectionSocket = serverSocket.accept();
                if(clients.size() < maxSize)
                {
                    clients.add(connectionSocket);
                }
                else{
                    for (Socket client : clients){
                        PrintWriter outToClient = new PrintWriter(client.getOutputStream(), true);
                        
                        outToClient.println("close connection");
      
                      }
                      serverSocket.close();

                    break;
                }
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                String clientSentence = inFromClient.readLine();
                System.out.println(clientSentence);
                for (Socket client : clients){
                  PrintWriter outToClient = new PrintWriter(client.getOutputStream(), true);
                  outToClient.println("Hello from Client"+clients.size());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
