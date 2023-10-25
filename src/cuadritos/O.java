/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

import cuadritos.cuadro;
import cuadritos.Formas;
import java.awt.Color;

/**
 *
 * @author marti
 */
public class O extends Formas{
    
    public O(){
        crear(Color.YELLOW);
        
    }
    
    public void setXY(int x, int y){
        /*
        ■ ■
        ■ ■  
        */
        
        c[0].x =x;
        c[0].y =y;
        c[1].x =c[0].x;
        c[1].y =c[0].y + cuadro.TAMAÑO;
        c[2].x =c[0].x + cuadro.TAMAÑO;
        c[2].y =c[0].y;
        c[3].x =c[0].x + cuadro.TAMAÑO;
        c[3].y =c[0].y + cuadro.TAMAÑO;
    }
    
    public void getDireccion1(){
    
        /*
        ■ ■
        ■ ■
        */
        

        temp[0].x = c[0].x;
        temp[0].y = c[0].y;
        temp[1].x = c[0].x - cuadro.TAMAÑO;
        temp[1].y = c[0].y;
        temp[2].x = c[0].x + cuadro.TAMAÑO;
        temp[2].y = c[0].y;
        temp[3].x = c[0].x + cuadro.TAMAÑO;
        temp[3].y = c[0].y;
        
        actualizarXY(1);
    }
    public void getDireccion2(){
    }
    public void getDireccion3(){
    }
    public void getDireccion4(){
    }
            
    
}

    
