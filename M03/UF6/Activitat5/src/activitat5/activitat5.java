/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activitat5;

/**
 *
 * @author poved
 */


public class activitat5 extends javax.swing.JFrame {
    
    static class Persona {
        private String nom;
        private String cognom;
        private String esport;
        private String edat;
        private boolean vegetaria;
        
        public Persona (String nom, String cognom, String esport, String edat, boolean vegetaria) {
            this.nom = nom;
            this.cognom = cognom;
            this.esport = esport;
            this.edat = edat;
            this.vegetaria = vegetaria;

        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getCognom() {
            return cognom;
        }

        public void setCognom(String cognom) {
            this.cognom = cognom;
        }

        public String getEsport() {
            return esport;
        }

        public void setEsport(String esport) {
            this.esport = esport;
        }

        public String getEdat() {
            return edat;
        }

        public void setEdat(String edat) {
            this.edat = edat;
        }

        public boolean isVegetaria() {
            return vegetaria;
        }

        public void setVegetaria(boolean vegetaria) {
            this.vegetaria = vegetaria;
        }
        
        
    }


    /**
     * Creates new form activitat5
     */
    public activitat5() {
        initComponents();
        Persona[] arrayPersona = new Persona[4];
        arrayPersona[0] = new Persona("Oriol", "Poveda", "Tennis", "19", false);
        arrayPersona[1] = new Persona("Ayman", "Kaddar", "Futbol", "21", true);
        arrayPersona[2] = new Persona("Maria", "Navarro", "Dormir", "19", false);
        arrayPersona[3] = new Persona("Hector", "Garzon", "Dormir", "20", false);
        
        for (int i = 0; i < arrayPersona.length; i++) {
            jTable1.setValueAt(arrayPersona[i].getNom(), i, 0);
            jTable1.setValueAt(arrayPersona[i].getCognom(), i, 1);
            jTable1.setValueAt(arrayPersona[i].getEsport(), i, 2);
            jTable1.setValueAt(arrayPersona[i].getEdat(), i, 3);
            jTable1.setValueAt(arrayPersona[i].isVegetaria(), i, 4);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nom", "Cognom", "Esport", "Edat", "Vegetarià?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(activitat5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(activitat5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(activitat5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(activitat5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new activitat5().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

