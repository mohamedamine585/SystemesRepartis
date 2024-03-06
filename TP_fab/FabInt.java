package TP_fab;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * FabInt
 */
public interface FabInt extends Remote , Serializable{

    public  ReverseInt newReverse() throws RemoteException;
}

    

