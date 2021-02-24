/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activitat2;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

    
class MyFrame extends JFrame implements ActionListener {
    
    
    
    int width = 800;
    int heigth = 500;
    int x = (1920 - width) / 2;
    int y = (1080 - heigth) / 2;
    
    MyFrame(String title) {
        super(title);
        setBounds(x,y,width,heigth);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new FlowLayout());
        setBounds(x,y,width,heigth);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getActionCommand().equals("Vermell")) {
           getContentPane().setBackground(Color.red);
           repaint();
       } else if (e.getActionCommand().equals("Verd")) {
           getContentPane().setBackground(Color.green);
           repaint();
       } else if (e.getActionCommand().equals("Blau")) {
          getContentPane().setBackground(Color.blue);
           repaint();
       } else if (e.getActionCommand().equals("Gris")) {
           getContentPane().setBackground(Color.gray);
           repaint();
       }
    }
    
}

public class Activitat2 {
 
    public static void main(String[] args) {
        // TODO code application logic here
        
        MyFrame frame = new MyFrame("Actividad 2");
        frame.setVisible(true);
        
    }
    
}
