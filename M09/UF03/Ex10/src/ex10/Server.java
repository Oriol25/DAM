
package ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server extends javax.swing.JFrame implements Runnable {

    boolean serverStatus = false;
    
    Socket client;
    ServerSocket server;
    static int numClient;
    String cadena;
    
    public Server() {
        initComponents();
    }
    
    public Server(Socket clientConnectat, ServerSocket server) {
	this.client = clientConnectat;
	this.server = server;
	this.numClient ++;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btStart = new javax.swing.JButton();
        jlEstatServer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        clientesSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btStart.setText("Iniciar Servidor");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        jlEstatServer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlEstatServer.setText("Servidor apagado");

        jLabel1.setText("Clientes Totales:");

        clientesSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlEstatServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clientesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(clientesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btStart)
                .addGap(18, 18, 18)
                .addComponent(jlEstatServer)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        
        int numClients = Integer.parseInt(clientesSpinner.getValue().toString());
        
        if (!serverStatus && numClients > 0) {
            btStart.setText("Parar Servidor");
            jlEstatServer.setText("Servidor funcionando");
            
            try {
                iniciarServidor(numClients);
            } catch (IOException ex) {
                
            }
            
            serverStatus = true;
        } else if (serverStatus) {
            btStart.setText("Iniciar Servidor");
            jlEstatServer.setText("Servidor apagado");
            
            pararServidor();
            
            serverStatus = false;
        }

        
    }//GEN-LAST:event_btStartActionPerformed

    public static void iniciarServidor(int numClients) throws IOException {

        int numPort = 60000;
        ServerSocket servidor = new ServerSocket(numPort);

        Runnable[] arrayRunnable = new Runnable[numClients];
        Thread[] arrayThread = new Thread[numClients];
	
        // Determinem les vegades que es conectaran els clients
        for (int i = 0; i < arrayRunnable.length; i++) {

            Socket clientConnectat = servidor.accept();

            // Runnable
            arrayRunnable[i] = new Server(clientConnectat, servidor);

            // Thread
            arrayThread[i] = new Thread(arrayRunnable[i]);
            arrayThread[i].start();

        }
    }
    
    public static void pararServidor() {
        
    }
    
    @Override
    public void run() {

        try {
            PrintWriter fsortida = null;
            BufferedReader fentrada = null;

            System.out.println("Esperant connexió... ");
            System.out.println("Client " + this.numClient + " connectat... ");

            //FLUX DE SORTIDA AL CLIENT
            fsortida = new PrintWriter(this.client.getOutputStream(), true);


            //FLUX D'ENTRADA DEL CLIENT
            fentrada = new BufferedReader(new InputStreamReader(this.client.getInputStream()));


            while ((cadena = fentrada.readLine()) != null) {

                fsortida.println(cadena);
                                
                if (!cadena.equals("*") && !cadena.equals("")) {
                    System.out.println("Rebent: "+cadena);
                } else {
                    break;
                }

            }
                          
            fentrada.close();
            fsortida.close();
            this.client.close();
            this.server.close();
			
	} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btStart;
    private javax.swing.JSpinner clientesSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlEstatServer;
    // End of variables declaration//GEN-END:variables


}
