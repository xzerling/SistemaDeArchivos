package app.model;

import java.util.ArrayList;

public class Directorio {

    private ArrayList<String> nombres;
    private ArrayList<Integer> posicion;

    public Directorio(ArrayList<Integer> pos, ArrayList<String> nombres) {
        this.posicion = pos;
        this.nombres = nombres;
    }
    public Directorio() {
        this.posicion = new ArrayList<Integer>();
        this.nombres = new ArrayList<String>();
    }

    public ArrayList<String> getNombres() {
        return nombres;
    }

    public void setNombres(ArrayList<String> nombres) {
        this.nombres = nombres;
    }

    public ArrayList<Integer> getPos() {
        return posicion;
    }

    public void setPos(ArrayList<Integer> pos) {
        this.posicion = pos;
    }
    

}
