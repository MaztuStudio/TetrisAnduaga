/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cuadritos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author marti
 */
public class cuadro extends Rectangle{
    public int x, y;
    public static final int TAMAÑO = 30;
    public Color c;
    
    public cuadro(Color c){
        this.c = c;
    }
    public void draw(Graphics2D g2){
        g2.setColor(c);
        g2.fillRect(x,y, TAMAÑO, TAMAÑO);
    }
    
}
