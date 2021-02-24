/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activitat1;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class MyFrame extends JFrame implements ActionListener {
    JPanel panel;
    
    JButton bFc;
    JButton bCf;
    JButton bBorrar;
    JLabel l;
    JLabel fc;
    JLabel cf;
    JTextField f;
    JTextField c;
    
         
    int width = 300;
    int heigth = 400;
    int x = (1920 - width) / 2;
    int y = (1080 - heigth) / 2;
    
    MyFrame(String title) {
        super(title);
        setBounds(x,y,width,heigth);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new FlowLayout());
        setBounds(x,y,width,heigth);  
        
        l = new JLabel("Conversor");
        fc = new JLabel("Pasar de F a C");
        f = new JTextField(4);
        cf = new JLabel("Pasar de C a F");
        c = new JTextField(4);
        
        bFc = new JButton("Passar de F a C");
        bCf = new JButton("Pasar de C a F");
        bBorrar = new JButton("Borrar");
        
        bFc.addActionListener(this);
        bCf.addActionListener(this);
        bBorrar.addActionListener(this);
        
        add(l);
        add(fc);
        add(f);
        add(cf);
        add(c);
        add(bFc);
        add(bCf);
        add(bBorrar);  

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getActionCommand().equals("Passar de F a C")) {
           int far = Integer.parseInt(f.getText());
           int cel = (far -32) * 5 / 9;
           c.setText(String.valueOf(cel));
           
       } else if (e.getActionCommand().equals("Pasar de C a F")) {
           int cel = Integer.parseInt(c.getText());
           int far = (cel * 9 / 5) + 32;
           f.setText(String.valueOf(far));
           
           
       } else if (e.getActionCommand().equals("Borrar")) {
           f.setText("");
           c.setText("");
       }
       
    }
    
}

public class Activitat1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MyFrame frame = new MyFrame("Title");
        frame.setVisible(true);
        
    }
    
}
