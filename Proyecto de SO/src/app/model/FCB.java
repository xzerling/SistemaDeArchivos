package app.model;




import java.util.ArrayList;


public class FCB {
    private int tamañoArchivo;
    private int tamañoBloques;
    private char[] contenido;
    private ArrayList<Bloque> bloques;
    private ArrayList<Integer> posicion;


    public FCB(int tamBloques) {  
        this.tamañoBloques = tamBloques;
        this.bloques = new ArrayList<>();
        this.posicion = new ArrayList<>();
        
        this.contenido = new char[tamBloques];
        
        for (int i=0; i<tamBloques; i++){
            contenido[i]=' ';
        }
    }

    public FCB(int tamBloques, int tamArchivo) {  
        this.tamañoBloques = tamBloques;
        this.tamañoArchivo = tamArchivo;
        this.bloques = new ArrayList<>();
        this.posicion = new ArrayList<>();
        
        this.contenido = new char[tamBloques];
        
        for (int i=0; i<tamBloques; i++){
            contenido[i]=' ';
        }
    }

    public ArrayList<Integer> getPosicion() {
        return posicion;
    }

    public void setPosicion(ArrayList<Integer> posicion) {
        this.posicion = posicion;
    }
    
   
    public ArrayList<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(ArrayList<Bloque> bloques) {
        this.bloques = bloques;
    }

    public int getTamañoBloques() {
        return tamañoBloques;
    }

    public void setTamañoBloques(int tamaño) {
        this.tamañoBloques = tamaño;
    }

    public char[] getContenido() {
        return contenido;
    }

    public void setContenido(char[] contenido) {
        this.contenido = contenido;
    }

    public int getTamañoArchivo() {
        return tamañoArchivo;
    }

    public void setTamañoArchivo(int tamañoArchivo) {
        this.tamañoArchivo = tamañoArchivo;
    }

    
    
}
