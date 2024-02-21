package TP3;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

// Define the remote interface
interface MyRemoteInterface extends Remote {
    int add(int a, int b) throws RemoteException;
}

// Implement the remote interface
class MyRemoteImplementation implements MyRemoteInterface {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}

public class MyRMIServer {
    public static void main(String[] args) {
        try {
            MyRemoteImplementation remoteObject = new MyRemoteImplementation();

            MyRemoteInterface stub = (MyRemoteInterface) UnicastRemoteObject.exportObject(remoteObject, 0);

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.bind("MyRemoteService", stub);

            System.out.println("Server is ready to accept requests.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
