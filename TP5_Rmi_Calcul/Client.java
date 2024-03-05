package TP5_Rmi_Calcul;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Get the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Look up the remote object by name
            OpString stub = (OpString) registry.lookup("OpString");

            // ask operation from user
             
            askOperationFromUser(stub);
           

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

   static public void askOperationFromUser(OpString stub){
        try {
            Scanner scanner = new Scanner(System.in);
            String inputstring ;
             int MethodChoise = 1;
            int index;
            while(MethodChoise != 0){
                System.out.println("Choose a method :");
                System.out.println("1 : Reverse");
                System.out.println("2 : Split");
                System.out.println("0 : Quit");
               
                   
            
                 MethodChoise = scanner.nextInt();
                if(MethodChoise == 1){
                    System.out.println("Write a string");
                    inputstring = scanner.next();
                    System.out.println(stub.Reverse(inputstring));
        
        
                }
                else if(MethodChoise == 2){
                    
                    System.out.println("Write a string");
                    inputstring = scanner.next();
                    System.out.println("Write an index");
                    index = scanner.nextInt();
                    if(index > 0){
                        System.out.println(stub.Split(inputstring,index));

                    }
                }
            }
            scanner.close();
           
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
       
    }
}
