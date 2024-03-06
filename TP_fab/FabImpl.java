package TP_fab;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FabImpl extends UnicastRemoteObject implements FabInt  {

    protected FabImpl() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public ReverseInt newReverse() throws RemoteException {
        return new ReverseImpl();
    }
    
}
