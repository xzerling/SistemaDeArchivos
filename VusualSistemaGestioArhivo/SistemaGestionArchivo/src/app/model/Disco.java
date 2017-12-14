package app.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;


public class Disco 
{
    private File archivoDeDisco;
    private int numSectors;
    private int tamSector;
    
    public Disco(int numSectors, int tamSector)
    {
        this.numSectors = numSectors;
        this.archivoDeDisco = new File("Disco");
        this.tamSector=tamSector;
    }
    
    public void montarDisco()
    {
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(this.archivoDeDisco);
            pw = new PrintWriter(fichero);
            Sector modelo = new Sector(this.tamSector);
            
            for(int i=0; i<this.numSectors; i++)
            {
                pw.println(modelo.getContenido());               
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {  
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }    
    
    public Sector leerSector(int numSector)
    {		
        if(this.numSectors >= numSector && numSector >= 0)
        {
            Sector bloque = new Sector(this.tamSector);
            Scanner lector;
            try 
            {
                lector = new Scanner(this.archivoDeDisco);
                                        
                for (int i=0; i<numSectors && lector.hasNextLine(); i++) 
                {
                    char linea[] = lector.nextLine().toCharArray();
                    if(i == numSector)
                    {
                        bloque.setContenido(linea);
                        lector.close();
                        return bloque;
                    }                                             
                                                         
                }                        
                lector.close();
            } 
            catch (FileNotFoundException e) 
            {
                e.printStackTrace();
            }
			
            return bloque;
        }
        return null;
    }
	
    public void escribirSector(int numSector, Sector bloque)
    {
        
        Scanner lector;
        ArrayList<String> lineas = new ArrayList<>();
        try 
        {
            lector = new Scanner(this.archivoDeDisco);

            for (int i=0; i<numSectors && lector.hasNextLine(); i++) 
            {
                String linea = lector.nextLine();
                lineas.add(linea);

            }                        
            lector.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
		
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(this.archivoDeDisco);
            pw = new PrintWriter(fichero);
            
            for(int i=0; i<this.numSectors; i++)
            {
                if (i==numSector){
                    pw.println(bloque.getContenido());
                }
                else {
                    pw.println(lineas.get(i)); 
                }
                              
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {  
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        
        
    }
    
    
    

   
    
    public int getNumSectores() {
        return this.numSectors;
    }
}