package TP_fab;

public class ReverseImpl implements ReverseInt {
    
    ReverseImpl()
    {
        super();
    };
    @Override
    public String ReverseIt(String string) {

        String reversed = "";
        if(string != null){
            for (int i = string.length() - 1 ; i >= 0 ; i-- ){
                reversed += string.charAt(i);
            }
        }
      
        return reversed;
    }
    
}
