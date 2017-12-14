/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

/**
 *
 * @author christian
 */
public class Bloque {
    
    private char[] contenido;
    private int id;
    
    public Bloque(int tamBloque) {
        this.id = -1;
        this.contenido = new char[tamBloque];
        
        for (int i=0; i<tamBloque; i++){
            contenido[i]=' ';
        }
    }
    
    public Bloque(int tamBloque, char[] conten) {
        this.id = id;
        this.contenido = new char[tamBloque];
        
        for (int i=0; i<conten.length; i++){
            contenido[i]=conten[i];
        }
        
        for (int i=conten.length; i<tamBloque; i++){
            contenido[i]=' ';
        }
    }
    
    public Bloque(int tamBloque, int id) {
        this.id = id;
        this.contenido = new char[tamBloque];
        
        for (int i=0; i<tamBloque; i++){
            contenido[i]='*';
        }
    }
    
    public Bloque(int tamBloque, int bytesOcupar, int id) {
        this.id = id;
        this.contenido = new char[tamBloque];
        
        for (int i=0; i<bytesOcupar; i++){
            contenido[i]='*';
        }
        for (int i=bytesOcupar; i<tamBloque; i++){
            contenido[i]=' ';
        }
    }

    public char[] getContenido() {
        return contenido;
    }

    public void setContenido(char[] contenido) {
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
