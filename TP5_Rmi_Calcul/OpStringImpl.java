package TP5_Rmi_Calcul;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OpStringImpl extends UnicastRemoteObject implements OpString {
   OpStringImpl()throws RemoteException{}
    @Override
    public String Reverse(String string) throws RemoteException {
        String reversedstr = "";
        for(int i = string.length()  - 1 ; i >= 0;i--){
            reversedstr+= string.charAt(i);
        }
        return reversedstr;
    }
    @Override
    public String Split(String string,int index) throws RemoteException {
        String[] array = new String[2];
        array[0] = string.substring(0, index-1);
        array[1] = string.substring(index);
      
        return array[0] + " " + array[1];
    }

}
