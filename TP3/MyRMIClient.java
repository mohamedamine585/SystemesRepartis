package TP3;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyRMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            MyRemoteInterface remoteObject = (MyRemoteInterface) registry.lookup("MyRemoteService");

            int result = remoteObject.add(10, 7);

            System.out.println("Result from remote method invocation: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
