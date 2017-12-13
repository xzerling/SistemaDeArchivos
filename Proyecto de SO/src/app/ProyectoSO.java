package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import app.model.Sector;
import app.model.Directorio;
import app.model.Disco;
import app.model.FAT;
import app.model.FCB;

public class ProyectoSO
{

    /**
     * @param args the command line arguments
     */
    private static Directorio directorio;
    private static Disco disco;
    private static FAT fat;
    private static int tamSectors;
    private static int numSectors;
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException
    {        
        numSectors = 512;
        tamSectors = 512;
        
        Scanner scan = new Scanner(System.in);

        cargarDisco(numSectors,tamSectors);
        
        int opcion = 0;
            Scanner scan2 = new Scanner(System.in);
            
            do{

                System.out.println("1) Format");
                System.out.println("2) Create");
                System.out.println("3) Remove");
                System.out.println("4) Read At");
                System.out.println("5) Write At");
                System.out.println("6) Print File");
                System.out.println("7) List");
                System.out.println("0) Exit");
                System.out.println();
                System.out.print("Opcion : ");
                try{
                    opcion = Integer.parseInt(scan.nextLine());
                    if (opcion>=0 && opcion<=7)
                    {
                        System.out.println("Opción "+opcion);
                    }
                    else
                    {
                        System.out.println("Opción invalida");
                        
                    }
                }
                catch(Exception e )
                {
                    System.out.println("Opción invalida");
                    
                }
                String nombre, texto;
                int tamaño,pos;
                switch(opcion)
                {
                    case 1:
                        System.out.println("\nFormateando...");
                        formato(numSectors, tamSectors);                        
                        break;
                    case 2:
                        System.out.println("\nCrear Archivo");
                       
                        
                        System.out.println("Ingrese el nombre");
                        nombre = scan2.nextLine();
                        
                        System.out.println("Ingrese el tamaño");
                        try{
                            tamaño = Integer.parseInt(scan2.nextLine());
                            if (tamaño>0) 
                            {
                                System.out.println("Tamaño Ingresado ");
                            }
                            else
                            {
                                while(tamaño<=0){
                                    System.out.println("Solo valores positivos");
                                    System.out.println("Ingrese el tamaño");
                                    tamaño = Integer.parseInt(scan2.nextLine());
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            do
                            {
                                System.out.println("Solo valores positivos");
                                System.out.println("Ingrese el tamaño");
                                tamaño = Integer.parseInt(scan2.nextLine());
                                if (tamaño>0)
                                {
                                    break;
                                }
                            } while (true);
                            
                        }
                        crear(tamaño, nombre);
                        break;
                    case 3:
                        System.out.println("\nEliminar archivo");
                        System.out.println("Ingrese el nombre");
                        nombre = scan2.nextLine();
                        remove(nombre);
                        break;
                    case 4:
                        System.out.println("\nLeer archivo");
                        System.out.println("Ingrese el nombre");
                        nombre = scan2.nextLine();
                        System.out.println("Ingrese la posicion de inicio");
                        try
                        {
                           pos = Integer.parseInt(scan2.nextLine());
                            if (pos>=0) 
                            {
                                System.out.println("Posición Ingresado ");
                            }
                            else
                            {
                                while(pos<0)
                                {
                                    System.out.println("Solo valores positivos");
                                    System.out.println("Ingrese La Posición");
                                    pos = Integer.parseInt(scan2.nextLine());
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            do 
                            {
                                System.out.println("Solo valores positivos");
                                System.out.println("Ingrese la Posición");
                                pos = Integer.parseInt(scan2.nextLine());
                                if (pos>=0)
                                {
                                    break;
                                }
                            } while (true);
                            
                        }
                        readAt(nombre, pos);                        
                        break;
                    case 5:
                        System.out.println("\nEscribiendo archivo");
                        System.out.println("Ingrese el nombre");
                        nombre = scan2.nextLine();
                        System.out.println("Ingrese la posicion");
                        try{
                           pos = Integer.parseInt(scan2.nextLine());
                            if (pos>=0) 
                            {
                                System.out.println("Posición Ingresado ");
                            }
                            else
                            {
                                while(pos<0)
                                {
                                    System.out.println("Solo valores positivos");
                                    System.out.println("Ingrese La Posición");
                                    pos = Integer.parseInt(scan2.nextLine());
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            do 
                            {
                                System.out.println("Solo valores positivos");
                                System.out.println("Ingrese la Posición");
                                pos = Integer.parseInt(scan2.nextLine());
                                if (pos>=0) 
                                {
                                    break;
                                }
                            } while (true);
                            
                        }
                        
                        System.out.println("Ingresar texto");
                        texto = (scan2.nextLine());
                        writeAt(nombre, pos, texto);                        
                        break;
                    case 6:
                        System.out.println("\nImprimir archivo");
                        System.out.println("Ingrese el nombre");
                        nombre = scan2.nextLine();
                        printFile(nombre);                        
                        break;
                    case 7:
                        System.out.println("\nDirectorio: ");
                        list();                        
                        break;
                }

            }while(opcion!=0);
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
    
    public static void crear(int tamaño, String nombre)
    {
        if(nombre.length()<=8 && esUnico(nombre))
        {
            int totalSectores = 0;

            if(tamaño%512!=0){
                totalSectores = tamaño/512+2;
            }
            else{
                totalSectores = tamaño/512+1;
            }

            if(fat.espacioDisponible(totalSectores))
            {
                int numSector = fat.asignarSector();
                reescribirFAT();
                FCB sectorFCB = new FCB(tamSectors,tamaño);
                for(int i=0;i<(totalSectores-1); i++)
                {
                    int numSectorI = fat.asignarSector();
                    reescribirFAT();
                    Sector bn = new Sector(tamSectors,numSectorI);
                    
                    if (i==totalSectores-2)
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
                System.out.println("Archivo creado");
            }
            else
                System.out.println("No hay espacio suficiente");
        }
        else
        {
            
            if(nombre.length()>9)
                System.out.println("El tamaño maximo de nombre son 8 caracteres");
            else
                System.out.println("El nombre ya ha sido utilizado");
        }
    }
    
    public static void remove(String nombre)
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
        if(!alerta)
        {
            System.out.println("El archivo no existe");
        }
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
    
    public static void readAt(String nombre, int posInicio)
    {
        int i = 0;
        boolean alerta = false;
        
        for(String name : directorio.getNombres())
        {
            if(name.equals(nombre))
            {                
                int posFCB = directorio.getPos().get(i);
                Sector sectorfcb = disco.leerSector(posFCB);                  
                FCB fcb = convertirSectorToFCB(sectorfcb);
                
                if (fcb.getTamañoArchivo()<posInicio)
                {
                    System.out.println("Posicion Incorrecta");
                    break;
                }

                System.out.println("Contenido del archivo desde byte: " + posInicio);
                int contador = 1;
                int min = 0;
                int max = contador*tamSectors;
                
                for (Integer id: fcb.getPosicion())
                {
                    Sector bloque = disco.leerSector(id);
                    String contenido = String.valueOf(bloque.getContenido());
                    
                    if (posInicio<max && posInicio>=min)
                    {
                        int inicio = posInicio-min;
                        imprimirSector(inicio,contenido);
                    }
                    else
                    {
                        if (posInicio<min)
                        {
                            System.out.println(contenido);
                        }
                    }
                    min = max;
                    contador++;
                    max = contador*tamSectors;
                }
                alerta = true;
                break;
            }
            i++;
        }
        if(alerta == false)
        {
            System.out.println("El archivoexiste");
        }
    }
  
    public static void writeAt(String nombre, int pos, String escribir)
    {
        
        int i = 0;
        boolean alerta = false;
        FCB fcb = new FCB(tamSectors);
        
        for(String name : directorio.getNombres())
        {
            if(name.equals(nombre))
            {             
                int posFCB = directorio.getPos().get(i);
                Sector sectorfcb = disco.leerSector(posFCB);                   

                fcb = convertirSectorToFCB(sectorfcb);
                
                int byteFinal = pos+escribir.length();
                
                if (fcb.getTamañoArchivo()<byteFinal)
                {
                    System.out.println("No hay espacio suficiente");
                    break;
                }
                for (Integer id: fcb.getPosicion())
                {
                    
                    Sector bloque = disco.leerSector(id);
                    bloque.setId(id);
                    fcb.getSectors().add(bloque);
                    
                }
                alerta = true;
                break;
            }
            i++;
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
        
        if(!alerta)
        {
            System.out.println("El archivo no existe");
        }
    }
     
    public static void printFile(String nombre)
    {
        int i = 0;
        boolean alerta = false;
        
        for(String name : directorio.getNombres()){
            if(name.equals(nombre))
            {
                int posFCB = directorio.getPos().get(i);
                Sector sectorfcb = disco.leerSector(posFCB);                 
                FCB fcb = convertirSectorToFCB(sectorfcb);

                System.out.println("Contenido del archivo: " + nombre);
                
                fcb.getPosicion().stream().map((id) -> disco.leerSector(id)).forEachOrdered((bloqueLeido) ->
                {
                    System.out.println(String.valueOf(bloqueLeido.getContenido()));
                });
                alerta = true;
                break;
            }
            i++;
        }
        if(!alerta)
        {
            System.out.println("El archivo no existe");
        }
    }

    public static void list()
    {
        
        int i = 0;
        
        if(!directorio.getNombres().isEmpty())
        {
            System.out.println("Nombre"+"\t"+"Tamaño"+"\t\t"+"Sectors");
            for(String nombre : directorio.getNombres())
            {
                int posFCB = directorio.getPos().get(i); 
                Sector b = disco.leerSector(posFCB);
                FCB fcb = convertirSectorToFCB(b);
                System.out.println(nombre + "\t" + fcb.getTamañoArchivo() + " (bytes)" + "\t" + fcb.getPosicion().size());
                i++;
            }
            System.out.println("Fin del directorio");
        }
        else
          System.out.println("Directorio esta vacio");
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

    private static void imprimirSector(int value, String contenido) {
        char[] cont = contenido.toCharArray();
        
        for (int i = 0; i < cont.length; i++) {
            if (i>=value){
                System.out.print(cont[i]);
            }
        }
        System.out.print("\n");
    }
    
    
}
