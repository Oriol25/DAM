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

public class TestFrame2 {
    
    public static void main (String[] args) {
        
        int width = 800;
        int heigth = 500;
        int x = (1920 - width) / 2;
        int y = (1080 - heigth) / 2;
        
        JFrame master = new JFrame("Click to Close Everthing");
        JFrame temp = new JFrame("Click to Close Just This");
        
        master.setVisible(true);
        master.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        master.setBounds(0,0,width,heigth);
        
        temp.setVisible(true);
        temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        temp.setBounds(1920 - width, 1080 - heigth, width, heigth);
        
        
        
    }
    
}
