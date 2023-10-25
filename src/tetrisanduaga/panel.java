/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetrisanduaga;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author marti
 */
public class panel extends JPanel implements Runnable{
    public static final int WIDTH=1280, HEIGHT=720; //tamaño del panel
    final int FPS = 60; //cuantas veces pa a actualizar por segundo
    Thread hJuego; //thread del juego
    dibujarC dc; //instancia de la clase dibujarComponentes
    
    
     private ImageIcon shispop = new ImageIcon("C:/Users/marti/Documents/NetBeansProjects/LineaDirecta/src/tetris/2silly.gif");
    
    public panel() throws IOException{
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        iniciar();
        dc = new dibujarC();
        
        
    }
    
    public void iniciar(){
        hJuego = new Thread(this);
        hJuego.start();
        
    }
    
    @Override
    public void run(){
        double intervaloDib = (1000000000/FPS), delta=0;
        long tAnterior = System.nanoTime();
        long tActual;
        
        while(hJuego != null){
            tActual = System.nanoTime();
            //delta es el tienpo desde que se renderizo el frame
            delta += (tActual - tAnterior)/ intervaloDib;
            tAnterior = tActual;
            if(delta >=1){
                actualizar();
                repaint();
                delta--;
            }
            
        }
        
    } 
    
    private void actualizar(){
        dc.actualizar();
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        dc.draw(g2);
        shispop.paintIcon(this, g, 0, 150);
        
    }
    
}
