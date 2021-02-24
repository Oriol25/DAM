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

public class TestFrame1 {
    
    public static void main (String[] args) {

        int width = 800;
        int heigth = 500;
        int x = (1920 - width) / 2;
        int y = (1080 - heigth)/2;
        
        
        JFrame frame = new JFrame("Test Frame 1");
        //frame.setSize(800,500);
        frame.setVisible(true);
        frame.setBounds(x,y, width, heigth);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
}
