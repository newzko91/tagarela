
package servidor;


import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Servidor {

    
    public static void main(String[] args) {
        
        ArrayList<PrintStream> clientes = new ArrayList<>();
        
        try{        
            
            ServerSocket server = new ServerSocket(5000); //Aqui você define qual porta de entrada será usada quando o cliente solicitar a conexão
            Socket socket;         
                        
            while(true){ //Roda sempre que receber novas conexões 
                
                socket = server.accept();   
                
                clientes.add(new PrintStream(socket.getOutputStream())); //Guarda o endereço do cliente
                
                Mensagem mensagem = new Mensagem(socket,clientes);

                    
                }                         
        }    
        catch(IOException e){            
             e.printStackTrace(); 
        }
        
    }
    
}
