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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;



public class TestFrame4 extends JFrame implements ActionListener {

    JButton bChange;   
    
    int width = 800;
    int heigth = 500;
    int x = (1920 - width) / 2;
    int y = (1080 - heigth) / 2;
    
    public TestFrame4 () {
    
        bChange = new JButton("Click Me");
        setLayout(new FlowLayout());
        setBounds(x,y,width,heigth);
        bChange.addActionListener(this); 
        add(bChange);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        float h;
        float s;
        float b;
        
        h = (float) (Math.random()*360+1);
        s = (float) (Math.random()*100+1);
        b = (float) (Math.random()*100+1);
        getContentPane().setBackground(Color.getHSBColor(h, s, b)); 
        repaint();
        System.out.println("H {" + h + "} S {" + s + "} B {" + b + "}");
            
    }
    
    public static void main (String[] args) {
     
        TestFrame4 frame = new TestFrame4();
        frame.setVisible(true);
        
    }
}
