package app.model;




import java.util.ArrayList;

public class BitMap {

    private ArrayList<Integer> estadoEspacio;

    public BitMap(Disco disco) {
        this.estadoEspacio = new ArrayList<Integer>();
        this.estadoEspacio.add(1);
        this.estadoEspacio.add(1);
        for(int i=2; i<disco.getNumSectores(); i++){
            this.estadoEspacio.add(0);
        }
    }

    public BitMap(ArrayList<Integer> espaciosLibres) {
        this.estadoEspacio = espaciosLibres;
    }
    
    public ArrayList<Integer> getEspacios(){
        return this.estadoEspacio;
    }
    
    public void addSectorDisponible(int sector){
        this.estadoEspacio.set(sector, 0);       
    }
    
    public int getSectorDisponible()
    {     
        
        for (int i = 0; i < estadoEspacio.size(); i++) {
            if (estadoEspacio.get(i)==0){
                estadoEspacio.set(i, 1);
                return i;
            }
        }
        
        return -1;
    }

    public boolean hayEspacio(int sectores){
        int cont=0;
        
        for (int i = 0; i < estadoEspacio.size(); i++) {
            if (estadoEspacio.get(i)==0){
                cont++;
            }
        }
        
        if(cont>=sectores)
            return true;
        return false;
    }
}