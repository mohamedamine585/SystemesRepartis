package TP5_Rmi_Calcul;
import java.io.Serializable;
import java.rmi.*;

public interface OpString extends Remote , Serializable {
    public String Reverse(String string) throws RemoteException;
    public String Split(String string,int index) throws RemoteException;


}

