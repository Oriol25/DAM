/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestFrame;

/**
 *
 * @author poved
 */
import java.awt.*;
import javax.swing.*;

class MyFrame extends JFrame {
    JPanel panel;
    JButton jbutton;
    int width = 460;
    int heigth = 200;
    int x = (1920 - width) / 2;
    int y = (1080 - heigth) / 2;
    
    MyFrame(String title) {
        super(title);
        setBounds(x,y,width,heigth);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        jbutton = new JButton("NORTH");
        add(jbutton, BorderLayout.NORTH);
        
        jbutton = new JButton("WEST");
        add(jbutton, BorderLayout.WEST);
        
        jbutton = new JButton("EAST");
        add(jbutton, BorderLayout.EAST);
        
        jbutton = new JButton("CENTER");
        add(jbutton, BorderLayout.CENTER);
        
        jbutton = new JButton("SOUTH");
        add(jbutton, BorderLayout.SOUTH);
        
    }
    
}

public class TestFrame3  {
    
    public static void main (String[] args) {
        
        MyFrame frame = new MyFrame("Hello");
        frame.setVisible(true);
        
    }
    
}
