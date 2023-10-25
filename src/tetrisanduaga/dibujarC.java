/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetrisanduaga;

import cuadritos.cuadro;
import cuadritos.Formas;
import cuadritos.I;
import cuadritos.J;
import cuadritos.L;
import cuadritos.S;
import cuadritos.T;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import tetris.O;
import tetris.Z;

/**
 *
 * @author marti
 */
public class dibujarC {
    final int width = 360, height= 600; //dimensiones del area de juego
    public static int xIzquierda, xDerecha, yTop, yBot; // coordenadas de las paredes del area de juego
    Formas formaActual; //forma actual (I,L,J,S,Z,O,T)
    final int FORMA_INICIO_X, FORMA_INICIO_Y; // Coordenadas donde aparecen las figuras
    Formas formaSiguiente; ////forma siguiente (I,L,J,S,Z,O,T)
    final int FORMA_SIGUIENTE_X, FORMA_SIGUIENTE_Y; // coordenadas para que aparezca en el cuadrito
    public static ArrayList<cuadro> cuadrosEstaticos = new ArrayList<>(); // Lista donde guardaremos las coordenadas de las formas no activas
    public boolean fin = false; //si ya perdiste o no
    public static int intervaloCaida = 60; //cuantas veces va a actualizar antes de que pase un segundo
    
    
    public dibujarC() throws IOException{
        // centramos el panel en el centro de la pantalla {
        xIzquierda = (panel.WIDTH/2) - (width/2); // (1280 / 2) - (360 / 2) = 460
        xDerecha = xIzquierda + width; // 460 + 360 el espacio de la pantalla mas el tamaño del panel
        yTop = 50;
        yBot = yTop + height; // 50 + 600 el espacio entre el panel y el fin de la ventana mas el tamaño del panel
        //}
        
        // asignamos valores para que aparezca en el centro del area de juego
        FORMA_INICIO_X = xIzquierda + (width/2)- cuadro.TAMAÑO;
        FORMA_INICIO_Y = yTop  + cuadro.TAMAÑO;
        
        
        // asignamos valores para que aparezca en el ccuadrito de al lado
        FORMA_SIGUIENTE_X = xDerecha + 175;
        FORMA_SIGUIENTE_Y = yTop + 500;
        
        //elegir la forma inicial
        formaActual = elegir();
        //asignamos las coordenadas
        formaActual.setXY(FORMA_INICIO_X, FORMA_INICIO_Y);
        
        //elegir la forma inicial
        formaSiguiente = elegir();
        //asignamos las coordenadas
        formaSiguiente.setXY(FORMA_SIGUIENTE_X, FORMA_SIGUIENTE_Y);
        
        
    }
    private Formas elegir(){
        Formas f = null;
        int i = new Random().nextInt(7);
        switch(i){
        case 0: f = new L(); System.out.println("L"); break;
        case 1: f = new J(); System.out.println("J");break;
        case 2: f = new T(); System.out.println("T");break;
        case 3: f = new O(); System.out.println("O");break;
        case 4: f = new I(); System.out.println("I");break;
        case 5: f = new Z(); System.out.println("Z");break;
        case 6: f = new S(); System.out.println("S");break;
    }
     return f;
    }
    public void actualizar(){
        //si no has perdido
        if(fin==false){
            //checamos si ya colisiono con algo abajo
        if(formaActual.activo == false){
            //si no esta activo lo metemos a la lista
             cuadrosEstaticos.add(formaActual.c[0]);
             cuadrosEstaticos.add(formaActual.c[1]);
             cuadrosEstaticos.add(formaActual.c[2]);
             cuadrosEstaticos.add(formaActual.c[3]);
             
             
             //si se queda en las coordenadas de creacion termina el juego
             if(formaActual.c[0].x == FORMA_INICIO_X && formaActual.c[0].y == FORMA_INICIO_Y){
                 fin = true;
             }
            
             //luego reemplazamos la forma actual por la siguiente
             formaActual = formaSiguiente;
             //movemos la forma a las coordenadas de inicio
             formaActual.setXY(FORMA_INICIO_X, FORMA_INICIO_Y);
             // elegimos la siguiente figura
             formaSiguiente = elegir();
             //dibujamos la forma siguiente en el cuadrito
             formaSiguiente.setXY(FORMA_SIGUIENTE_X, FORMA_SIGUIENTE_Y);
             //vemos si alguna linea esta completa
             checarLinea();
        }else{
            //checamos si el usuario ha presionado wasd en la clase formas
            formaActual.actualizar();
        }
        }
    }
    
    private void checarLinea(){
        //paredes del area de uego
        int x = xIzquierda;
        int y = yTop;
        //contador de que cuadro estamos evaluando (1-12)(cada linea tiene 12 cuadros)
        int cantCuadros = 0;
        
        //mientras x sea menor que la pared derecha && y sea menor que la pared inferior
        while(x < xDerecha && y< yBot){
            //vamos a evaluar cada cuadro de la lista
            for(int i=0; i< cuadrosEstaticos.size(); i++){
                // si las coordenadas de algun cuadro que tengamos en la lista coincide con el bloque que estamoe escaneando aumentamos la cantidad de cuadros evaluados
                if(cuadrosEstaticos.get(i).x == x && cuadrosEstaticos.get(i).y == y){
                     cantCuadros++;
                }
            }
            //sumamos un cuadro a x
            x+= cuadro.TAMAÑO;
            
            //si el evaluador llego a la xderecha termino la linea
            if(x == xDerecha){
                
                if(cantCuadros == 12){
                    //si llevamos 12 cuadros es que tenemos una linea llena
                    for(int i = cuadrosEstaticos.size()-1; i> -1; i--){
                        //hacemos el condeo desde arriba hacia abajo porque si no truena ¯\_(ツ)_/¯
                        //si el valor de y del cuadro en el contador es igual 
                        if(cuadrosEstaticos.get(i).y == y){
                            //removemos el valor del array donde i,  se repite 12 veces para quitar la linea
                            cuadrosEstaticos.remove(i);
                        }
                    }
                    //ya que borramos la linea bajamos los demas bloques
                for(int i=0; i < cuadrosEstaticos.size(); i++){
                    //si el bloque esta arriba de y, lo movemos para abajo
                    if(cuadrosEstaticos.get(i).y < y){
                        cuadrosEstaticos.get(i).y+=cuadro.TAMAÑO;
                    }
                    
                }
                }
                //reseteamos
                cantCuadros =0;
                x = xIzquierda;
                //aumentamos y con el valor de un cuadro
                y += cuadro.TAMAÑO;
            }
        }
        
    }
    public void draw(Graphics2D g2){
        //dibujar el area de juegos
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(xIzquierda-4, yTop-4, width+8, height+8);
        
        // frame de figura siguiente
        int x = xDerecha+100, y = yBot -200;
        g2.drawRect(x, y, 200, 200);
        g2.setFont(new Font("Azonix", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("SIGUE", x+50, y+60);
        
        //dibujar forma actual
        if(formaActual != null){
            formaActual.draw(g2);
        }
        
        //dibujar la forma siguiente
            formaSiguiente.draw(g2);
            
            
            
            
            //dibujar la forma que acaba de terminar
            for(int i=0; i< cuadrosEstaticos.size(); i++){
                cuadrosEstaticos.get(i).draw(g2);
                
                //dibujar el game over
                g2.setColor(Color.RED);
                g2.setFont(g2.getFont().deriveFont(50f));
                if(fin == true){
                    g2.drawString("GAME OVER", xIzquierda+70, yTop+320);
                }
            }
            
    }
    
    
    public static void dibujarShispop(Image img, Graphics2D g) {
        g.drawImage(img, 0, 200, null);
    }
    
    
}
