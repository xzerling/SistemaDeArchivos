package app.model;




import java.util.ArrayList;


public class FCB {
    private int tamañoArchivo;
    private int tamañoSectors;
    private char[] contenido;
    private ArrayList<Sector> bloques;
    private ArrayList<Integer> posicion;


    public FCB(int tamSectors) {  
        this.tamañoSectors = tamSectors;
        this.bloques = new ArrayList<>();
        this.posicion = new ArrayList<>();
        
        this.contenido = new char[tamSectors];
        
        for (int i=0; i<tamSectors; i++){
            contenido[i]=' ';
        }
    }

    public FCB(int tamSectors, int tamArchivo) {  
        this.tamañoSectors = tamSectors;
        this.tamañoArchivo = tamArchivo;
        this.bloques = new ArrayList<>();
        this.posicion = new ArrayList<>();
        
        this.contenido = new char[tamSectors];
        
        for (int i=0; i<tamSectors; i++){
            contenido[i]=' ';
        }
    }

    public ArrayList<Integer> getPosicion() {
        return posicion;
    }

    public void setPosicion(ArrayList<Integer> posicion) {
        this.posicion = posicion;
    }
    
   
    public ArrayList<Sector> getSectors() {
        return bloques;
    }

    public void setSectors(ArrayList<Sector> bloques) {
        this.bloques = bloques;
    }

    public int getTamañoSectors() {
        return tamañoSectors;
    }

    public void setTamañoSectors(int tamaño) {
        this.tamañoSectors = tamaño;
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
