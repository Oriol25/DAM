package ex06;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;

public class Ex06 extends javax.swing.JFrame {    
    
    public Ex06() {
        initComponents();
        }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGap(0, 300, Short.MAX_VALUE));
        pack();
        }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    }
                }
            }
        catch (Exception ex) {
            java.util.logging.Logger.getLogger(Ex06.class.getName()).log(
                java.util.logging.Level.SEVERE, null, ex);
            }       
        Ex06 f = new Ex06();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Naus Espaials");
        f.setContentPane(new PanelNau());
        f.setSize(800, 800);
        f.setVisible(true);
        }
    }


class PanelNau extends JPanel implements Runnable, KeyListener {
    private int numNaus=3;    
    Nau[] nau;
    private Nau nauNostre;
    
    Random rand = new Random();
        
    int velocitatN=(rand.nextInt(3)+5)*10;
    int dXN = 0;
    
    public PanelNau(){        
        nau = new Nau[numNaus];
        
        for (int i=0;i<nau.length;i++) {
            Random rand = new Random();
            int velocitat=(rand.nextInt(3)+5)*10;
            int posX=rand.nextInt(100)+30;
            int posY=rand.nextInt(100)+30;
            int dX=rand.nextInt(3)+1;
            int dY=rand.nextInt(3)+1;
            nau[i]= new Nau(i,posX,posY,dX,dY,velocitat);
            }
        nauNostre = new Nau(numNaus,100,600,dXN,0,velocitatN);
        
        Thread n = new Thread(this);
        n.start();
        
        addKeyListener(this);
        setFocusable(true);
        
        }

    public void run() {
        System.out.println("Inici fil repintar");
        while(true) {
            try { Thread.sleep(100);} catch(Exception e) {} // espero 0,1 segons
            //System.out.println("Repintant");
            repaint();            
            }                   
        }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0; i<nau.length;++i) nau[i].pinta(g); 
        nauNostre.pinta(g);
        //TODO Pintar nau nostre
        
    }

    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == 37) {
           nauNostre.moureEsquerra();
        } else if (e.getKeyCode() == 39) {
           nauNostre.moureDreta();
        }
        
        //System.out.println("Key pressed code = " + e + ", char = " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }


    class  Nau extends Thread {
        private int numero;
        private int x,y;
        private int dsx,dsy,v;
        private int tx = 10;
        private int ty = 10;

        private String img = "/images/nau.jpg";
        private Image image;

        public Nau(int numero, int x, int y, int dsx, int dsy, int v ) {
            this.numero = numero;
            this.x=x;
            this.y=y;
            this.dsx=dsx;
            this.dsy=dsy;
            this.v=v;
            image = new ImageIcon(Nau.class.getResource("nau.png")).getImage();
            Thread t = new Thread(this);
            t.start();
            }

        public int velocitat (){
            return v;
            }

        public void moure (){
            x=x + dsx;
            y=y + dsy;
            // si arriva als marges ...
            if ( x>= 700 - tx || x<= tx) dsx = - dsx;
            if ( y >= 750 - ty || y<=ty ) dsy = - dsy;
        }
        
        public void moureEsquerra() {
            x += -4;
        }
        
        public void moureDreta() {
            x += 4;
        }

        public void pinta (Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.image, x, y, null);
        }
        
        public void moureStart () {
            
        }


        public void run() {
            while (true) {
                System.out.println("Movent nau numero " + this.numero);
                try { Thread.sleep(this.v); } catch (Exception e) {}
                moure();
            }
        }
    }


}
