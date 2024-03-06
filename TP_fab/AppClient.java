package TP_fab;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppClient {
    public static void main(String[] args) {
        try {

            // connect to registry
           Registry registry = LocateRegistry.getRegistry("localhost", 1099);

           // get the FabImpl object

           FabInt clientstub = (FabInt) registry.lookup("Fab");

           // first Reverse impl
           ReverseInt reverse1 =  (ReverseInt) clientstub.newReverse();
           System.out.println(reverse1.ReverseIt("hello world !"));

           ReverseInt reverse2 =  (ReverseInt) clientstub.newReverse();
           System.out.println(reverse2.ReverseIt("Bye world !"));
        } catch (Exception e) {
           System.out.println(e);
        }
    }
}
