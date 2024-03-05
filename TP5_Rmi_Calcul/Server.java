package TP5_Rmi_Calcul;

import java.net.ServerSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args)  {
        try {

            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Bind a calculImpl to "calcul"
            OpStringImpl opstring = new OpStringImpl();
            registry.bind("OpString", opstring);

             // create tcp server

             ServerSocket serverSocket = new ServerSocket(6789);
             System.out.println("Server listening on port :" + serverSocket.getLocalPort());
             while (true) {
                
                
             }

            

        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
