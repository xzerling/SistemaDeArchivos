package sistemaarchivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import static jdk.nashorn.internal.parser.TokenType.EOF;

/**
 *
 * @author christian
 */

public class Disco 
{
    private File archivoDeDisco;
    private int numBloques;
    private int tamBloque;
    
    public Disco(int numBloques, int tamBloque)
    {
        this.numBloques = numBloques;
        this.archivoDeDisco = new File("Disco");
        this.tamBloque=tamBloque;
    }
    
    public void montarDisco()
    {
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(this.archivoDeDisco);
            pw = new PrintWriter(fichero);
            Bloque modelo = new Bloque(this.tamBloque);
            
            for(int i=0; i<this.numBloques; i++)
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
    
    public Bloque leerBloque(int numBloque)
    {		
        if(this.numBloques >= numBloque && numBloque >= 0)
        {
            Bloque bloque = new Bloque(this.tamBloque);
            Scanner lector;
            try 
            {
                lector = new Scanner(this.archivoDeDisco);
                                        
                for (int i=0; i<numBloques && lector.hasNextLine(); i++) 
                {
                    char linea[] = lector.nextLine().toCharArray();
                    if(i == numBloque)
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
	
    public void escribirBloque(int numBloque, Bloque bloque)
    {
        
        Scanner lector;
        ArrayList<String> lineas = new ArrayList<>();
        try 
        {
            lector = new Scanner(this.archivoDeDisco);

            for (int i=0; i<numBloques && lector.hasNextLine(); i++) 
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
            
            for(int i=0; i<this.numBloques; i++)
            {
                if (i==numBloque){
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
        return this.numBloques;
    }
}