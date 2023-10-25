/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetrisanduaga;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author marti
 */
public class KeyHandler  implements KeyListener{
    public static boolean up,down,left,right;
    
    public void keyTyped(KeyEvent e){
        
    }
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            up = true;
        }
        if(code == KeyEvent.VK_A){
            left = true;
        }
        if(code == KeyEvent.VK_S){
            down = true;
        }
        if(code == KeyEvent.VK_D){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
}
