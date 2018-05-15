
package cliente;

import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

public class Cliente {

   
    public static void main(String[] args) throws ParseException {
       
        /*
        
        JFrame frame = new JFrame("Bate-papo APS Meio Ambiente");
        String nome = showInputDialog(frame,"Digite seu nome: ", "", PLAIN_MESSAGE);
        
        ChatGUI chat = new ChatGUI(nome);
        chat.setVisible(true);
        System.exit(0);
        
        String nome = showInputDialog(null,"Digite seu nome: ", "", PLAIN_MESSAGE);
        
        */
        
        JFrame frame = new JFrame("Bate-papo APS Meio Ambiente");
        String nome = "";
        
        
        do {
            nome = showInputDialog(frame, "Digite seu nome: ", "Bate-papo APS Meio Ambiente", PLAIN_MESSAGE);

            if (nome == null) {
                System.exit(0);
            } else if(nome.length()<5) {
                JOptionPane.showMessageDialog(null, "Nome do usuário está em branco\nOU\nNome do usuário < 5 caracteres");
            } else {

                ChatGUI chat = new ChatGUI(nome);
                chat.setVisible(true);
            }

        } while (nome.length() < 5);
            
            
            
        } 
        
        
    }
    
