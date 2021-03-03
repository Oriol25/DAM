/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activitat8;

import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Alumne
 */
public class activitat8 extends javax.swing.JFrame {

        int contX = 0;
        int contW = 0;
        int contPuntos = 0;
        String tabla[][] = new String[4][4];

    public activitat8() {
        initComponents();
        

    }
   
    private void rellenarTabla() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                jTable1.setValueAt("?", i, j);
            }
        }
    }
    
    private void rellenarTablaSecreta () {
     
        int random;
        
        Random rand = new Random();
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                random = rand.nextInt(3);
                switch(random) {
                    case 0: tabla[i][j] = "O";
                        break;
                    case 1: 
                        if (contX != 2) {
                            tabla[i][j] = "X";
                            contX++;
                        } else {
                            tabla[i][j] = "O";
                        }
                        break;
                    case 2: 
                        if (contW != 3) {
                            tabla[i][j] = "W";
                            contW++;
                        } else {
                            tabla[i][j] = "O";
                        }                        
                        break;      
                }
            }
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlRecord = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPunts = new javax.swing.JLabel();
        btPartida = new javax.swing.JButton();
        btSoritr = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPuntsRecord = new javax.swing.JLabel();
        jlRecord2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlRecord.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jlRecord.setText("Rècord:");
        jlRecord.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Punts: ");

        jPunts.setBackground(new java.awt.Color(255, 255, 255));
        jPunts.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jPunts.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPunts.setText("0");
        jPunts.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPunts.setFocusable(false);

        btPartida.setText("Començar Pantalla");
        btPartida.setActionCommand("Començar Pantalla");
        btPartida.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPartidaActionPerformed(evt);
            }
        });

        btSoritr.setText("Sortir");
        btSoritr.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btSoritr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSoritrActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""}
            },
            new String [] {
                " ", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(60);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jPuntsRecord.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPuntsRecord.setText("0");
        jPuntsRecord.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jlRecord2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jlRecord2.setText("punts");
        jlRecord2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlRecord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPuntsRecord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlRecord2))
                    .addComponent(jPunts, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btSoritr, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlRecord)
                            .addComponent(jPuntsRecord)
                            .addComponent(jlRecord2))
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPunts)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSoritr, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSoritrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSoritrActionPerformed
        JOptionPane.showConfirmDialog(null, "Puntos: " + contPuntos, "El juego del año", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        dispose();
    }//GEN-LAST:event_btSoritrActionPerformed

    private void btPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPartidaActionPerformed

            btPartida.setText("Reiniciar Programa");
            contX = 0;
            contW = 0;
            rellenarTabla();
            rellenarTablaSecreta();
            contPuntos = 0;
            jTable1.setEnabled(true);
            jPunts.setText("0");
    }//GEN-LAST:event_btPartidaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        int fila = jTable1.getSelectedRow();
        int columna = jTable1.getSelectedColumn();
        
        if (jTable1.getValueAt(fila, columna) == "?") {
            jTable1.setValueAt(tabla[fila][columna], fila, columna);
            if ("X".equals(tabla[fila][columna])) {
                    JOptionPane.showConfirmDialog(null,"Puntos: " + contPuntos, "El juego del año", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (Integer.valueOf(jPuntsRecord.getText()) < contPuntos) {
                        jPuntsRecord.setText(String.valueOf(contPuntos));
                    }
                    
                    jTable1.setEnabled(false);
            } else if ("W".equals(tabla[fila][columna])) {
                if (contPuntos != 0) {
                    contPuntos = contPuntos * 2;
                    jPunts.setText(String.valueOf(contPuntos));
                }
                
            } else {
                contPuntos++;
                jPunts.setText(String.valueOf(contPuntos));
            }
        }
        
        
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(activitat8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(activitat8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(activitat8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(activitat8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new activitat8().setVisible(true);
                
            }
            
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPartida;
    private javax.swing.JButton btSoritr;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jPunts;
    private javax.swing.JLabel jPuntsRecord;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlRecord;
    private javax.swing.JLabel jlRecord2;
    // End of variables declaration//GEN-END:variables
}
