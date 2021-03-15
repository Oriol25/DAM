
package ex07;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;

public class Ex07 extends javax.swing.JFrame  {

    public Ex07() {
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
            java.util.logging.Logger.getLogger(Ex07.class.getName()).log(
                java.util.logging.Level.SEVERE, null, ex);
            }       
        Ex07 f = new Ex07();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Naus Espaials");
        f.setContentPane(new PanelNau());
        f.setSize(800, 800);
        f.setVisible(true);
    }
}

class PanelNau extends JPanel implements Runnable, KeyListener {
    private int numNaus=10;    
    Nau[] nau;
    Nau nauNostre;
    int y;
    private static int contaBalas = 0;
    Shot[] shots = new Shot[5];
    Shot shot;
    
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
        
        nauNostre = new Nau(numNaus,100,600,0,0,100);
        
        Thread n = new Thread(this);
        n.start();
        
        addKeyListener(this);
        setFocusable(true);
        
    }

    public void run() {
        System.out.println("Inici fil repintar");
        while(true) {
            try { Thread.sleep(1);} catch(Exception e) {} // espero 0,1 segons
            //System.out.println("Repintant");
            repaint();            
            }                   
        }

    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0; i<nau.length;++i) {
            if (nau[i] != null) {
                nau[i].pinta(g);
            }
        } 
   
        nauNostre.pinta(g);
        
        try {
            eliminarNau();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
            
        for(int i=0; i< shots.length; i++) {
            if (shots[i] != null) {
                y = shots[i].getY();
                if (y <= 0) {
                    shots[i].setRun(false);
                    shots[i]=null;
                } else {
                    shots[i].pintaDisparo(g);
                }
            }
        }
    }
    
    public synchronized void bala() {
        if (contaBalas < 5) {
            if (shots[contaBalas] == null) {
                shots[contaBalas] = new Shot(nauNostre.getX() + 20, nauNostre.getY() - +25, nauNostre.velocitat());
            }
        }
        
        contaBalas++;
    }
    
    public void eliminarNau() throws InterruptedException {
        int nauX;
        int nauY;
        int shotX;
        int shotY;
        double costat;
        int conta = 0;
        
        for(int i = 0; i < nau.length; i++) {
            for (int j = 0; j < shots.length; j++) {
                if(shots[j] != null && nau[i] != null) {
                    nauX = nau[i].getX();
                    shotX = shots[j].getX();
                    nauY = nau[i].getY();
                    shotY = shots[j].getY();
                    
                    costat = Math.sqrt(Math.pow((nauY - shotY), 2) + Math.pow((nauX - shotX), 2));
                    
                    if(costat < 35) {
                        shots[j].setRun(true);
                        nau[i].setRun(false);
                        nau[i] = null;
                        shots[j] = null;
                        
                        for(int f = 0; f < nau.length; f++){
                            if(nau[f] == null){
                                conta++;
                            }
                            if(conta == nau.length){
                                Thread.sleep(2000);
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        } 
    }

    public static int getContaBalas() {
        return contaBalas;
    }

    public static void setContaBalas(int cont) {
        contaBalas = cont;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == 37) {
           nauNostre.moureEsquerra();
        } else if (e.getKeyCode() == 39) {
           nauNostre.moureDreta();
        } else if (e.getKeyCode() == 32) {
           bala();
        } 
        
        // System.out.println("Key pressed code = " + e + ", char = " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            nauNostre.stoped();
        }
        if (e.getKeyCode() == 39) {
            nauNostre.stoped();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    class Nau extends Thread {
        private int numero;
        private int x,y;
        private int dsx,dsy,v;
        private int tx = 10;
        private int ty = 10;

        private String url = "nau.png";
        private Image image; 
       
        private boolean run = true;
        
        public Nau(int numero, int x, int y, int dsx, int dsy, int v) {
            this.numero = numero;
            this.x=x;
            this.y=y;
            this.dsx=dsx;
            this.dsy=dsy;
            this.v=v;
            
            image = new ImageIcon(Nau.class.getResource(url)).getImage();
            
            Thread t = new Thread(this);
            t.start();
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int velocitat (){
            return v;
        }

        public void setRun(boolean run) {
            this.run = run;
        }

        public synchronized void moure (){
            x=x + dsx;
            y=y + dsy;
            // si arriva als marges ...
            if ( x>= 700 - tx || x<= tx) dsx = - dsx;
            if ( y >= 500 - ty || y<=ty ) dsy = - dsy;
        }
        
        public void moureEsquerra() {   
            if(!(x <= 0 - tx)) {
                this.dsx = -10;
            }
        }
        
        public void moureDreta() {
            if(!(x >= 440 - tx)) {
                this.dsx = 10;
            }
            
        }

        public synchronized void pinta (Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.image, x , y, null);
        }  
        
        public void run() {
            while (run) {
                try { Thread.sleep(this.v); } catch (Exception e) {}
                moure();
            }
        }

        private void stoped() {
            this.dsx = 0;
        }
    }
    
    class Shot extends Thread { 

        private int x, y, v;

        private int k = 0;

        private Image imagen;
        private String URL = "bala.png";
        private boolean run = true;

        public Shot (int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;

            imagen = new ImageIcon(PanelNau.Nau.class.getResource(URL)).getImage();

            Thread t = new Thread(this);
            t.start();

        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void setRun(boolean run) {
            this.run = run;
        }

        public void moure () {
            int dsy = 30;
            y = y - dsy;

            if (y <= 0) {
                if (k < 1) {
                    PanelNau.setContaBalas(0);
                    k++;
                }

            }

        }

        public void run () {
            while (run) {
                try { 
                    Thread.sleep(this.v); 
                } catch (Exception e) {}
                moure();
            }
        }

        public synchronized void pintaDisparo (Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(this.imagen, this.x, this.y, null);
        }

    }
}
