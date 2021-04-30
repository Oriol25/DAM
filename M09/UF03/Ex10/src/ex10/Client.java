/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumne
 */
public class Client extends javax.swing.JFrame {

    String host = "localhost";
    int port = 60000;
    Socket client;
    
    //FLUX DE SORTIDA AL SERVIDOR
    PrintWriter fsortida;
		
    //FLUX D'ENTRADA AL SERVIDOR
    BufferedReader fentrada;
		
    //FLUX PER A ENTRADA ESTÀNDARD
    BufferedReader in;
    
    //USUARIO LOGUEADO
    boolean login = false;
    
    public Client() throws IOException {
        this.client = new Socket(host, port);
        fsortida = new PrintWriter(client.getOutputStream(), true);
        fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
        in = new BufferedReader(new InputStreamReader(System.in));
        
        initComponents();
    }

    
        
    String cadena, eco;
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taChat = new javax.swing.JTextArea();
        tfChat = new javax.swing.JTextField();
        jbSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taChat.setColumns(20);
        taChat.setRows(5);
        taChat.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        taChat.setFocusable(false);
        jScrollPane1.setViewportView(taChat);

        jbSend.setText("Send");
        jbSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tfChat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbSend))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSend))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSendActionPerformed
        
        if (tfChat.getText().startsWith("//log ") && !login) {
            // PARA CUANDO EL USUARIO QUIERA INICIAR SESION
            String name = tfChat.getText().subSequence(6, tfChat.getText().length()).toString();
            //taChat.setText(taChat.getText() + "Bienvenido " + name + "\n");
            fsortida.println(tfChat.getText());
            
            login = true;     
            
            try {
                eco = fentrada.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        } else if (tfChat.getText().equals("//h")) {
            //MOSTRAR TODOS LOS COMANDOS
            taChat.setText(taChat.getText() + "//log nombreDelUsuario = Iniciar sesion " + "\n");
            taChat.setText(taChat.getText() + "//h = Lista de comandos" + "\n");
            taChat.setText(taChat.getText() + "//exit = salir" + "\n");
            taChat.setText(taChat.getText() + "//clear = Limpiar pantalla" + "\n");
            
        } else if (tfChat.getText().equals("//clear")) {
            //LIMPIAR PANTALLA<
            taChat.setText("");
        } else if (tfChat.getText().equals("//exit")) {
            //CERRAR EL CLIENTE
            
            fsortida.println("//exit");
            
            try {
                fsortida.close();
                fentrada.close();
                in.close();
                client.close();
                dispose();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (tfChat.getText().startsWith("//")) {
            // EN CASO DE INTRODUCIR UN COMANDO ERRONEO
            taChat.setText(taChat.getText() + "No es un comando valido!" + "\n");
            taChat.setText(taChat.getText() + "//log nombreDelUsuario = Iniciar sesion " + "\n");
            taChat.setText(taChat.getText() + "//h = Lista de comandos" + "\n");
            taChat.setText(taChat.getText() + "//exit = salir" + "\n");
            taChat.setText(taChat.getText() + "//clear = Limpiar pantalla" + "\n");
        
        } else if (login) {
            //ENVIAR MENSAJES AL SERVIDOR
            fsortida.println(tfChat.getText());
            
            try {
                eco = fentrada.readLine();
                taChat.setText(taChat.getText() + eco + "\n");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } else {
            // EL MENSAJE NO ES UN COMANDO NI EL USUARIO ESTA LOGUEADO
            System.out.println("ERROR");
        }

        tfChat.setText("");
        
    }//GEN-LAST:event_jbSendActionPerformed
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                Client client;
                
                try {
                    client = new Client();
                    client.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
      

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSend;
    private javax.swing.JTextArea taChat;
    private javax.swing.JTextField tfChat;
    // End of variables declaration//GEN-END:variables

}
