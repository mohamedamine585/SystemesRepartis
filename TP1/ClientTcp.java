package TP1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTcp {
   public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 8080);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            outToServer.println("Hello TCP Server");
             while (clientSocket.isConnected()) {
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String serverSentence = inFromServer.readLine();
                System.out.println(serverSentence);
                if(serverSentence.equals("close connection"))
                {
                  break;

                }
              
             }
             clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}