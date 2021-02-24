/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class TestFrame6 extends JFrame implements ActionListener {
    
    JTextField inText;
    JTextField outText;
    JButton bChange;
    
    int width = 200;
    int heigth = 150;
    int x = (1920 - width) / 2;
    int y = (1080 - heigth) / 2;
    
    public TestFrame6(String title) {
        super(title);
        inText = new JTextField(15);
        setLayout(new FlowLayout());
        add(inText);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        outText = new JTextField(15);
        setLayout(new FlowLayout());
        add(outText);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        bChange = new JButton("Click Me");
        setLayout(new FlowLayout());
        setBounds(x,y,width,heigth);
        bChange.addActionListener(this); 
        add(bChange);
        
    }
    
    public static void main (String[] args){
        TestFrame6 teg = new TestFrame6("Text Field");
        teg.setVisible(true);
        
        
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = inText.getText();
        outText.setText(name);
        repaint();
    }
    
}
