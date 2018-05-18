/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;

/**
 *
 * @author Paulo
 */
public class Login {
    
     static void nomeLogin() {

        JFrame frame = new JFrame("Bate-papo APS Meio Ambiente");
        String nome = "";

        do {

            nome = showInputDialog(frame, "Digite seu nome: ", "Bate-papo APS Meio Ambiente", PLAIN_MESSAGE);

            if (nome == null) {

                sairLogin();

            } else if (nome.length() < 5) {
                JOptionPane.showMessageDialog(null, "Nome do usuário está em branco\nOU\nNome do usuário < 5 caracteres");
            } else {

                ChatGUI chat = new ChatGUI(nome);
                chat.setVisible(true);
            }

        } while (nome.length() < 5);

    }

     static void sairLogin(){
          Object[] options = { "Confirmar", "Cancelar" };
          
           int opcao = JOptionPane.showOptionDialog(null, "Realmente deseja sair?", "Aviso",
                                              JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
               if( opcao == 0 || opcao == -1){
                   System.exit(0);
               }
               else{
                   nomeLogin();
               }
    }
    
}
