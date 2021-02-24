/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestFrame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestFrame5 extends JFrame implements ActionListener {

    JButton bQuit = new JButton("Clic here to Exit");   
    
    int width = 800;
    int heigth = 500;
    int x = (1920 - width) / 2;
    int y = (1080 - heigth) / 2;
    
    public TestFrame5 (String title) {
    
        super(title);
        setLayout(new FlowLayout());
        setBounds(x,y,width,heigth);
        bQuit.addActionListener(this);
        add(bQuit);
        
    }   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        System.exit(0);
        
    }
    
    public static void main (String[] args) {
     
        TestFrame5 frame = new TestFrame5("Button Quitter");
        frame.setVisible(true);
        
    }
    
}