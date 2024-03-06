package TP_fab;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * AppServer
 */
public class AppServer {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            FabImpl fabImpl = new FabImpl();
            registry.bind("Fab", fabImpl);

            while (true) {
                
            }
            

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}