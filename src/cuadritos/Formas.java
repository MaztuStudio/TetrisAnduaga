/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cuadritos;

import java.awt.Color;
import java.awt.Graphics2D;
import tetrisanduaga.KeyHandler;
import tetrisanduaga.dibujarC;

/**
 *
 * @author marti
 */
public class Formas {
    
    public cuadro c[] = new cuadro[4];//array para los 4 cuadros
    public cuadro temp[] = new cuadro[4];//array para guardar los valores del primer array temporalmente
    int caida = 0;//cuenta hasta 60 y luego actualiza la pantalla
    int direccion = 1; // con este evaluamos que forma se va a dibujar 
    boolean colisionIzquierda,colisionDerecha,colisionBot; //variables de colision con el area de juego
    public boolean activo = true; //la forma esta activa mientras no colisione con nada de abajo
    
    public void crear(Color co){
        //asignamos el color desde las respectivas clases de las formas
        c[0] = new cuadro(co);
        c[1] = new cuadro(co);
        c[2] = new cuadro(co);
        c[3] = new cuadro(co);
        temp[0] = new cuadro(co);
        temp[1] = new cuadro(co);
        temp[2] = new cuadro(co);
        temp[3] = new cuadro(co);
        
        
    }
    
    public void setXY(int x, int y){}
    
    public void actualizarXY(int direccion){
        //checamos colision para ver si al rotar colisionamos con el area de juego
        checarColisionRotacion();
        
        //si no esta colisionando con alguna pared
        if(colisionBot == false && colisionIzquierda == false && colisionDerecha == false){
           //le damos el valor de en que direccion esta la forma desde su respectiva clase
        this.direccion = direccion;
        //antes de pasar los valores debemos checar las colisiones, por eso usamos la variable temporal
        c[0].x = temp[0].x;
        c[0].y = temp[0].y;
        c[1].x = temp[1].x;
        c[1].y = temp[1].y;
        c[2].x = temp[2].x;
        c[2].y = temp[2].y;
        c[3].x = temp[3].x;
        c[3].y = temp[3].y;
            }
    
    }
    public void getDireccion1(){}
    public void getDireccion2(){}
    public void getDireccion3(){}
    public void getDireccion4(){}
    public void checarColisionMovimiento(){
        //reseteamos variables
    colisionIzquierda = false;
    colisionDerecha = false;
    colisionBot = false;
    
    //vemos si colisiona con algun cuadro
    checarColisionCuadros();
    
    //Pared Izquierda
    // Evaluamos las coordenadas de los 4 cuadros
    for(int i=0; i< c.length; i++){
        if(c[i].x == dibujarC.xIzquierda){
            // si alguna coincide con las coordenadas de la pared izquierda ponemos true
            colisionIzquierda=true;
        }
    }  
    //Pared Derecha
    // Evaluamos las coordenadas de los 4 cuadros
    for(int i=0; i< c.length; i++){
        // si alguna coincide con las coordenadas de la pared derecha ponemos true
        if(c[i].x + cuadro.TAMAÑO == dibujarC.xDerecha){
            colisionDerecha=true;
        }
    }
        //Pared Abajo
    // Evaluamos las coordenadas de los 4 cuadros
    for(int i=0; i< c.length; i++){
        // si alguna coincide con las coordenadas de la pared inferior ponemos true
        if(c[i].y + cuadro.TAMAÑO == dibujarC.yBot){
            colisionBot=true;
        }
    }
        
    }
    public void checarColisionRotacion(){
    //hace lo mismo que la anterior pero con los valores temporales,
    //que en este caso seria lo que la forma va a ser despues de rotar
    colisionIzquierda = false;
    colisionDerecha = false;
    colisionBot = false;
    
    checarColisionCuadros();
    
    //Pared Izquierda
    for(int i=0; i< c.length; i++){
        if(temp[i].x < dibujarC.xIzquierda){
            colisionIzquierda=true;
        }
    }  
    //Pared Derecha
    for(int i=0; i< c.length; i++){
        if(temp[i].x + cuadro.TAMAÑO > dibujarC.xDerecha){
            colisionDerecha=true;
        }
    }
        //Pared Abajo
    for(int i=0; i< c.length; i++){
        if(temp[i].y + cuadro.TAMAÑO > dibujarC.yBot){
            colisionBot=true;
        }
    }}
    public void checarColisionCuadros(){
        
        //evaluamos cada coordenada que tenemos en la lista
        for(int i = 0; i < dibujarC.cuadrosEstaticos.size(); i++){
            
            //guardamos las coordenadas del cuadro en la lista que estamos evaluando
            int X1 = dibujarC.cuadrosEstaticos.get(i).x;
            int Y1 = dibujarC.cuadrosEstaticos.get(i).y;
            
            //checar si colisiona abajo
            for(int i2 = 0; i2 < c.length; i2++){
                //si las coordenadas acutuales movidas un cuadro para abajo son las mismas que un elemento en la lista
                if(c[i2].y + cuadro.TAMAÑO == Y1 && c[i2].x == X1){
                    //le ponemos true
                    colisionBot=true;
                }
            }
            
            //checar si colisiona a la izquierda
            for(int i2 = 0; i2 < c.length; i2++){
                //si las coordenadas acutuales movidas un cuadro para la izquierda son las mismas que un elemento en la lista
                if(c[i2].x +- cuadro.TAMAÑO == X1 && c[i2].y == Y1){
                    //le ponemos true
                    colisionIzquierda=true;
                }
            }
            
            //checar  si colisiona a la derecha
            for(int i2 = 0; i2 < c.length; i2++){
                //si las coordenadas acutuales movidas un cuadro para la derecha son las mismas que un elemento en la lista
                if(c[i2].x + cuadro.TAMAÑO == X1 && c[i2].y == Y1){
                    //le ponemos true
                    colisionDerecha=true;
                }
            }
        }
        
    }
    
    
    public void actualizar(){
        
        //mover la pieza
        if(KeyHandler.up){
            //ya que asignamos el valor de direccion a la variable en actualizarXY podemos cambiar la direccion e la forma que este en pantalla
            switch(direccion){
                case 1: getDireccion2(); break;
                case 2: getDireccion3(); break;
                case 3: getDireccion4(); break;
                case 4: getDireccion1(); break;
            }
            //ya que terminemos reseteamos
            KeyHandler.up = false;
        }
        checarColisionMovimiento();
        
        if(KeyHandler.down){
            //si no hay nada abajo baja un cuadro
            if(colisionBot == false){
            c[0].y += cuadro.TAMAÑO;
            c[1].y += cuadro.TAMAÑO;
            c[2].y += cuadro.TAMAÑO;
            c[3].y += cuadro.TAMAÑO;
            }
            
            //reseteamos el contador de donde va 
            caida=0;
            KeyHandler.down=false;
        }
            //si no hay nada a la izquierda movemos un cuadro
        if(KeyHandler.left){
            
            if(colisionIzquierda == false){
            c[0].x -= cuadro.TAMAÑO;
            c[1].x -= cuadro.TAMAÑO;
            c[2].x -= cuadro.TAMAÑO;
            c[3].x -= cuadro.TAMAÑO;
            }
            //reseteamos el contador de donde va 
            caida=0;
            KeyHandler.left=false;
        }
            //si no hay nada a la derecha movemos un cuadro
        if(KeyHandler.right){
            if(colisionDerecha == false){
            c[0].x += cuadro.TAMAÑO;
            c[1].x += cuadro.TAMAÑO;
            c[2].x += cuadro.TAMAÑO;
            c[3].x += cuadro.TAMAÑO;
            }
            //reseteamos el contador de donde va 
            caida=0;
            KeyHandler.right=false;
        }
        
        
        if(colisionBot){
            //cuando toque el fondo deja de ser activo
            activo = false;
        }else{
        caida++; // aumenta cada frame
        if(caida == dibujarC.intervaloCaida){
            //movemos la forma un cuadro para abajo
            c[0].y += cuadro.TAMAÑO;
            c[1].y += cuadro.TAMAÑO;
            c[2].y += cuadro.TAMAÑO;
            c[3].y += cuadro.TAMAÑO;
            //reseteamos caida
            caida = 0;
        }
        }
    }
    
    public void draw(Graphics2D g2){
        //espacio para los cuadritos
        int margin=2;
        //usamos el atributo de color de la clase cuadro
        g2.setColor(c[0].c);
        //dibujamos la forma usando las coordenadas del array
        g2.fillRect(c[0].x + margin, c[0].y + margin, cuadro.TAMAÑO-(margin*2), cuadro.TAMAÑO-(margin*2));
        g2.fillRect(c[1].x + margin, c[1].y + margin, cuadro.TAMAÑO-(margin*2), cuadro.TAMAÑO-(margin*2));
        g2.fillRect(c[2].x + margin, c[2].y + margin, cuadro.TAMAÑO-(margin*2), cuadro.TAMAÑO-(margin*2));
        g2.fillRect(c[3].x + margin, c[3].y + margin, cuadro.TAMAÑO-(margin*2), cuadro.TAMAÑO-(margin*2));
        
    }
    
}
