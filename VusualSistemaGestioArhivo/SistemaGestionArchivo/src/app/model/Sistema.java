/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import app.model.*;
import app.model.Directorio;
import app.model.Disco;
import app.model.FAT;
import app.model.Sector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author YorchXD
 */
public class Sistema 
{
    public static Directorio directorio;
    public static Disco disco;
    public static FAT fat;
    public static int tamSectors;
    public static int numSectors;

    public static void sistema() throws IOException
    {
        numSectors = 512;
        tamSectors = 512;
        cargarDisco(numSectors,tamSectors);
    }
    
    public static void cargarDisco(int numSectors, int tamSectors) throws FileNotFoundException, IOException
    {
        
        disco = new Disco(numSectors, tamSectors);     
        
        ArrayList<String> nombres = new ArrayList<String>();
        ArrayList<Integer> pos = new ArrayList<Integer>();
        ArrayList<Integer> espacioLibre = new ArrayList<Integer>();
                
        File f = new File("Disco");
        
        if(f.exists())
        {   
            System.out.println("existe");
            BufferedReader entrada = new BufferedReader(new FileReader(f));
            String sector = entrada.readLine();
            if(compararVacio(sector) == false)
            {
                char[] linea = sector.toCharArray();
                int ini = 0;
                int fin = 0;
                boolean buscandoNombre = true;
                for (char c : linea)
                {
                    if (c!=' ')
                    {
                        if (c!='-')
                        {
                            fin++;
                        }
                        else
                        {
                            String text = sector.substring(ini, fin);
                            fin++;
                            ini = fin;
                            if (buscandoNombre == true)
                            {
                                nombres.add(text);
                                buscandoNombre = false;
                            }
                            else 
                            {
                                pos.add(Integer.parseInt(text));
                                buscandoNombre = true;
                            }
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                directorio = new Directorio(pos, nombres);
                sector = entrada.readLine();
                for(int i = 0 ; i<sector.length(); i++ )
                {
                    espacioLibre.add(Integer.parseInt(sector.charAt(i)+""));
                }
                fat = new FAT(espacioLibre);
            }
            else
            {
                formato(numSectors,tamSectors);
            }
        }
        else
        {
            formato(numSectors,tamSectors);
        }
    }
    
    private static boolean compararVacio(String sector)
    {
        Sector vacio = new Sector(tamSectors);
        String sec = String.valueOf(vacio.getContenido());
        if (sec.equals(sector))
        {
            return true;
        }
        
        return false;
    }
    
    public static void formato(int numSectors, int tamSectors)
    {
        disco = new Disco(numSectors,tamSectors);
        disco.montarDisco();
        directorio = new Directorio();
        fat = new FAT(disco);
        
        Sector org = new Sector(tamSectors);                
                
        char[] contenido = new char[tamSectors];
        contenido[0]='1';
        contenido[1]='1';
        
        for(int i = 2 ; i<tamSectors; i++)
        {
            contenido[i]='0';
        }
        
        org.setContenido(contenido);
        disco.escribirSector(1, org);
    }
    
    public static void crear(int tamaño, String nombre)
    {
        
        int totalSectores = 0;

        if(tamaño%512!=0){
            totalSectores = tamaño/512+2;
        }
        else{
            totalSectores = tamaño/512+1;
        }

        int numSector = fat.asignarSector();
        reescribirFAT();
        FCB sectorFCB = new FCB(tamSectors,tamaño);
        for(int i=0;i<(totalSectores-1); i++)
        {
            int numSectorI = fat.asignarSector();
            reescribirFAT();
            Sector bn = new Sector(tamSectors,numSectorI);

            if (i == totalSectores-2)
            {
                bn = new Sector(tamSectors, (tamaño%tamSectors), numSectorI);
            }
            sectorFCB.getSectors().add(bn);
            sectorFCB.getPosicion().add(numSectorI);
            disco.escribirSector(numSectorI, bn);
        }
        String salida = String.valueOf(sectorFCB.getTamañoArchivo());
        for (Sector bloque: sectorFCB.getSectors())
        {
            salida+="-"+String.valueOf(bloque.getId());
        }

        Sector fc = new Sector(tamSectors,salida.toCharArray());
        disco.escribirSector(numSector, fc);

        directorio.getNombres().add(nombre);
        directorio.getPos().add(numSector);

        salida = "";
        boolean registrandoNombre = true;

        int total = directorio.getNombres().size();

        for (int i=0 ; i<total; i++)
        {
            if (registrandoNombre)
            {
                salida+=directorio.getNombres().get(i)+"-";
                registrandoNombre = false;
            }
            if (registrandoNombre==false){
                salida+=directorio.getPos().get(i)+"-";
                registrandoNombre = true;
            }
        }

        Sector dir = new Sector(tamSectors,salida.toCharArray());

        disco.escribirSector(0, dir);       
    }
    
    private static void reescribirFAT()
    {
        String nuevo = "";
        for(int i=0; i<tamSectors; i++)
        {
            nuevo+=String.valueOf(fat.getEspacioActual().get(i));
        }
        Sector org = new Sector(tamSectors);
        org.setContenido(nuevo.toCharArray());
        disco.escribirSector(1, org);  
    }
    
    public static boolean estimarEspacioSuficiente(int tamanio)
    {
        int totalSectores = 0;

        if(tamanio%512!=0){
            totalSectores = tamanio/512+2;
        }
        else{
            totalSectores = tamanio/512+1;
        }
        return fat.espacioDisponible(totalSectores);
    }
    
    public static boolean esUnico(String nombre)
    {
        for (int i = 0; i < directorio.getNombres().size(); i++) 
        {
            if(directorio.getNombres().get(i).equals(nombre))
            {
                return false;
            }
        }
        return true;
    }
    
    public static boolean remove(String nombre)
    {
        int i = 0;
        boolean alerta = false;
        for(String name : directorio.getNombres())
        {
            if(name.equals(nombre))
            {
                directorio.getNombres().remove(i);
                int posFCB = directorio.getPos().get(i);
                directorio.getPos().remove(i);
                reescribirDirectorio();

                Sector sectorfcb = disco.leerSector(posFCB);
                FCB fcb = convertirSectorToFCB(sectorfcb);

                for (Integer id: fcb.getPosicion())
                {
                    Sector bloqueNuevo = new Sector(tamSectors);
                    disco.escribirSector(id, bloqueNuevo);
                    fat.agregarEspacio(id);
                    reescribirFAT();
                }

                Sector bloqueFCB = new Sector(tamSectors);
                disco.escribirSector(posFCB, bloqueFCB);
                
                alerta = true;
                break;
            }
            i++;
        }
        return alerta;
    }
    
    public static void reescribirDirectorio()
    {
        String salida = "";
        boolean registrandoNombre = true;

        int total = directorio.getNombres().size();

        for (int i=0 ; i<total; i++)
        {
            if (registrandoNombre)
            {
                salida+=directorio.getNombres().get(i)+"-";
                registrandoNombre = false;
            }
            if (registrandoNombre==false)
            {
                salida+=directorio.getPos().get(i)+"-";
                registrandoNombre = true;
            }
        }
        Sector dir = new Sector(tamSectors,salida.toCharArray());
        disco.escribirSector(0, dir);
    }
    
    private static FCB convertirSectorToFCB(Sector sectorfcb)
    {
        FCB salida = new FCB(tamSectors);
        salida.setContenido(sectorfcb.getContenido());
        
        char[] linea = sectorfcb.getContenido();
        boolean buscandoTamaño = true;
        int inicio=0;
        int fin = 0;
        String temporal = "";
        String tamaño = "";
        int posicion = 1;
        
        ArrayList<Integer> referencias = new ArrayList<>();
        
        for (char c: linea){
            if (c!=' '){
                if (c!='-'){
                    fin++;
                }
                else 
                {
                    if (buscandoTamaño)
                    {
                        tamaño = String.valueOf(linea).substring(inicio, fin);
                        salida.setTamañoArchivo(Integer.valueOf(tamaño));
                        fin++;
                        inicio=fin;
                        buscandoTamaño = false;
                    }
                    else
                    {
                        temporal = String.valueOf(linea).substring(inicio, fin);
                        referencias.add(Integer.valueOf(temporal));
                        fin++;
                        inicio=fin;
                    }
                }
            }
            else 
            {
                if (posicion==fin+1)
                {
                    temporal = String.valueOf(linea).substring(inicio, fin);
                    referencias.add(Integer.valueOf(temporal));
                }
                break;
            }
            posicion++;
        }
        
        for (Integer referencia : referencias)
        {
            salida.getPosicion().add(referencia);
        }
        
        return salida;
    }
    
    public static int buscarArchivo(String nombre)
    {
        int i=0;
        for(String name : directorio.getNombres())
        {
            if(name.equals(nombre))
            {                
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public static boolean verificarPosicion(int posInicio, int posArchivo)
    {
        int posFCB = directorio.getPos().get(posArchivo);
        Sector sectorfcb = disco.leerSector(posFCB);                  
        FCB fcb = convertirSectorToFCB(sectorfcb);
        
        if (fcb.getTamañoArchivo()<posInicio)
        {
            return false;
        }
        return true;
    }
        
    public static String readAt(String nombre, int posInicio, int posArchivo)
    {       
        
        int posFCB = directorio.getPos().get(posArchivo);
        Sector sectorfcb = disco.leerSector(posFCB);                  
        FCB fcb = convertirSectorToFCB(sectorfcb);
        String texto="";
        String contenido="";

        System.out.println("Contenido del archivo desde byte: " + posInicio);
        int contador = 1;
        int min = 0;
        int max = contador*tamSectors;
        

        for (Integer id: fcb.getPosicion())
        {
            System.out.println("primero");
            Sector bloque = disco.leerSector(id);
            contenido = String.valueOf(bloque.getContenido());
            System.out.println("segundo");
            if (posInicio<max && posInicio>=min)
            {
                System.out.println("if ini");
                int inicio = posInicio-min;
                texto=obtenerSector(inicio,contenido);
                System.out.println("if fin");
            }
            else
            {
                if (posInicio<min)
                {
                    System.out.println("else if ini");
                    texto+=contenido;
                    System.out.println("texto: "+texto);
                    System.out.println("else if fin");
                }
            }
            System.out.println("entro");
            min = max;
            contador++;
            max = contador*tamSectors;
        }
        System.out.println("contenido: "+contenido);
        return texto;
            
    }
    
    private static String obtenerSector(int value, String contenido) 
    {
        char[] cont = contenido.toCharArray();
        String texto="";
        for (int i = 0; i < cont.length; i++) {
            if (i>=value){
                texto+=cont[i];
                //System.out.println("burradas");
                System.out.print(cont[i]);
            }
        }
        System.out.println("text: "+texto);
        return texto;
    }
    
    public static String printFile(int posArchivo)
    {
        int posFCB = directorio.getPos().get(posArchivo);
        Sector sectorfcb = disco.leerSector(posFCB);                  
        FCB fcb = convertirSectorToFCB(sectorfcb);
        String contenido="";
        
        for (Integer id: fcb.getPosicion())
        {
            Sector bloque = disco.leerSector(id);
            contenido += String.valueOf(bloque.getContenido());
        }
        System.out.println("contenido: "+contenido);
        return contenido;
    }
    
    public static String list()
    {        
        int i = 0;
        String texto ="";
        if(!directorio.getNombres().isEmpty())
        {
            for(String nombre : directorio.getNombres())
            {
                int posFCB = directorio.getPos().get(i); 
                Sector b = disco.leerSector(posFCB);
                FCB fcb = convertirSectorToFCB(b);
                
                texto+="Nombre Archivo: " + nombre + "\n";
                texto+="Tamaño: " + fcb.getTamañoArchivo()  + "\n";
                texto+="Sector: " + fcb.getPosicion().size() +"\n\n";
                
                i++;
            }            
        }
        else
        {
            texto="Directorio vacio";
        }
        return texto;
        /*else
          System.out.println("Directorio esta vacio");*/
    }
    
    public static void writeAt(int pos, String escribir, int posArchivo)
    {
        
        int posFCB = directorio.getPos().get(posArchivo);
        Sector sectorfcb = disco.leerSector(posFCB);                   

        FCB fcb = convertirSectorToFCB(sectorfcb);
        for (Integer id: fcb.getPosicion())
        {
            Sector bloque = disco.leerSector(id);
            bloque.setId(id);
            fcb.getSectors().add(bloque);

        }        
        int inicioEscribir = pos;
        int contadorSectors = 1;
        for (Sector c: fcb.getSectors())
        {
            if (inicioEscribir<contadorSectors*tamSectors)
            {
                int disEscribir = (contadorSectors*tamSectors)-inicioEscribir;
                if (disEscribir>escribir.length())
                {
                    String corto = escribir;
                    String antiguo = String.valueOf(c.getContenido()).substring(escribir.length());
                    String nuevo = corto+antiguo;
                    
                    escribir = "";
                    
                    c.setContenido(nuevo.toCharArray());
                    disco.escribirSector(c.getId(),c);
                    inicioEscribir = contadorSectors*tamSectors;
                    
                }
                else 
                {
                    String corto = escribir.substring(0, disEscribir);
                    String antiguo = String.valueOf(c.getContenido()).substring(0, inicioEscribir);
                    String nuevo = antiguo+corto;

                    escribir = escribir.substring(disEscribir);

                    c.setContenido(nuevo.toCharArray());
                    disco.escribirSector(c.getId(),c);
                    inicioEscribir = contadorSectors*tamSectors;
                }
                contadorSectors++;
            }
            
            if (escribir.length()==0)
            {
                break;
            }
        }   
    }
    
     public static boolean verificarEspacio(int pos, String escribir, int posArchivo)
     {
        FCB fcb = new FCB(tamSectors);
        int posFCB = directorio.getPos().get(posArchivo);
        Sector sectorfcb = disco.leerSector(posFCB);                   

        fcb = convertirSectorToFCB(sectorfcb);

        int byteFinal = pos+escribir.length();

        if (fcb.getTamañoArchivo()<byteFinal)
        {
            return false;
        }
        return true;
     }
    
}
