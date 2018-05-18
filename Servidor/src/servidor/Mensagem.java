package servidor;


import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Mensagem {
    
    private Socket s;
    private ArrayList<PrintStream> clientes;
    
    public Mensagem(Socket s, ArrayList<PrintStream> clientes){
        this.s = s;
        this.clientes = clientes;
        Thread();
    }
    
    private void Thread(){
        
        Thread t = new Thread(new Runnable(){
            
            @Override
            public void run(){
                
                String mensagem = null; 
        
                try{

                    InputStreamReader isr = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(isr);

                    while((mensagem = br.readLine()) != null){
                        enviarMensagem(mensagem);

                    }

                }
                catch(Exception e){
                    e.printStackTrace();
                } 
                
            }
        });
        t.start();
    }
    
    private void enviarMensagem(String mensagem){
        for(int i = 0; i< clientes.size(); i++){
            
            clientes.get(i).println(mensagem);
            clientes.get(i).flush();
        
    }
        
    }
}

