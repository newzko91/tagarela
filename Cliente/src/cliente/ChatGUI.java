
package cliente;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*; // Importa net porque será utilizado Socket para enviar a mensagem
import static javax.swing.JOptionPane.*;

public class ChatGUI extends javax.swing.JFrame {
    
    private String nome;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader isr;
    private boolean rodar;

    //Construtor
    public ChatGUI(String nome) {
        
        initComponents();
        
        rodar = true;
        this.nome = nome;
        
        try{
        s = new Socket("127.0.0.1",5000);// Os parametros são "ip que irá se conectar","Porta que será utilizada"
                                         //Para enviar a mensagem para outro cliente, tem que adicionar o próprio ip
                                         //A porta de entrada deve ser habilitada para uso
        }
        catch(Exception e){
            
            showMessageDialog(null, "Não conseguiu se conectar ao Servidor","",ERROR_MESSAGE); // Apresenta o erro caso não seja estabelecida a conexão
            System.exit(0); //Fecha o programa
        }
        
        Thread();
    }
    
    private void Thread(){
    
        Thread t = new Thread(new Runnable() {
            
            String mensagem;
            
            @Override
            public void run() {
            
                try{
                
                   isr = new InputStreamReader(s.getInputStream());
                   br = new BufferedReader(isr);
                   
                   while((mensagem = br.readLine()) != null){
                       
                       mensagemRecebida.setText(mensagemRecebida.getText() + mensagem + "\n");
                       
                       if(!rodar){
                           break;
                           
                       }
                       
                   }
                }
                catch(IOException e){
                    
                    showMessageDialog(null,"Erro na conexão", "", ERROR_MESSAGE);
                    
                }
            }
        });
        
        t.start();
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        mensagemRecebida = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensagemEnviada = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 600));
        setResizable(false);

        mensagemRecebida.setEditable(false);
        mensagemRecebida.setColumns(20);
        mensagemRecebida.setRows(5);
        jScrollPane3.setViewportView(mensagemRecebida);

        mensagemEnviada.setColumns(20);
        mensagemEnviada.setRows(5);
        mensagemEnviada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mensagemEnviadaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(mensagemEnviada);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed

        
        String mensagem = nome + ": ";
        
       try{//Envia a mensagem e limpa a memória
           
            PrintStream ps = new PrintStream(s.getOutputStream());
            mensagem += mensagemEnviada.getText();
            
            ps.println(mensagem);
            ps.flush();//Serve para limpar a memória, mas é pouco utilizado
            mensagemEnviada.setText("");
       } 
       catch(IOException e){
           
           showMessageDialog(null,"Não enviou a mensagem", "", ERROR_MESSAGE);
           
       }
       
       

    }//GEN-LAST:event_btnEnviarActionPerformed

    private void mensagemEnviadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensagemEnviadaKeyPressed
       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String mensagem = nome + ": ";

            try {//Envia a mensagem e limpa a memória

                PrintStream ps = new PrintStream(s.getOutputStream());
                mensagem += mensagemEnviada.getText();

                ps.println(mensagem);
                ps.flush();//Serve para limpar a memória, mas é pouco utilizado
                
                mensagemEnviada.setText(null);
            } catch (IOException e) {

                showMessageDialog(null, "Não enviou a mensagem", "", ERROR_MESSAGE);

            }

        }
        
    }//GEN-LAST:event_mensagemEnviadaKeyPressed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed

       try{
           s.close();
           
           System.exit(0);
       }
       catch(Exception e){
           e.printStackTrace();
       }

    }//GEN-LAST:event_btnSairActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnSair;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea mensagemEnviada;
    private javax.swing.JTextArea mensagemRecebida;
    // End of variables declaration//GEN-END:variables
}
