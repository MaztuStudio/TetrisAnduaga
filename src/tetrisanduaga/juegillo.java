/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetrisanduaga;

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author marti
 */
public class juegillo {
    
    public static void main(String[] args) throws IOException{
        JFrame f = new JFrame("Tetris");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        panel p = new panel();
        f.add(p);
        f.pack(); // se adapta al tamaño de la window
        f.setLocationRelativeTo(null);
	f.setVisible(true);
        
    }
}
