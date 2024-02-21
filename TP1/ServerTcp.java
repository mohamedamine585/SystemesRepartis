package TP1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTcp {
    static int maxSize = 3;
    static int clientsNB = 0 ;
    static Socket[] clients = new Socket[maxSize];
     public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server TCP is listening on localhost:8080");
            while (true) {
                Socket connectionSocket = serverSocket.accept();
                if(clientsNB< maxSize)
                {
                    clients[clientsNB] =(connectionSocket);
                    clientsNB++;
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
                  outToClient.println("Hello from Client"+(clientsNB+1));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
