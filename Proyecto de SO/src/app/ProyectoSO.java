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
import app.model.BitMap;
import app.model.Sector;
import app.model.Directorio;
import app.model.Disco;
import app.model.FCB;

public class ProyectoSO {

    /**
     * @param args the command line arguments
     */
    private static Directorio directorio;
    private static Disco disco;
    private static BitMap bitMap;
    private static int tamSectors;
    private static int numSectors;
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        int opc;
        
        numSectors = 512;
        tamSectors = 512;
        
        Scanner scan = new Scanner(System.in);

        cargarDisco(numSectors,tamSectors);
        
        System.out.println("1.- Ejecutar Script");
        System.out.println("2.- Ejecutar Menu");
        System.out.println("0.- Salir");
        System.out.print("Opcion: ");
        try
        {
            opc = Integer.parseInt(scan.nextLine());
            while (opc<0 || opc>2) {
                System.out.println("La opción ingresada no es valida, por favor intente nuevamente");
                System.out.println("..............................................................");
                System.out.println("1.- Ejecutar Script");
                System.out.println("2.- Ejecutar Menu");
                System.out.println("0.- Salir");
                System.out.println("..............................................................");
                System.out.print("Opcion: ");
                opc = Integer.parseInt(scan.nextLine());
                
            }
            System.out.println("Ejecutando la Opción "+opc+"..........");
             
        }
        catch (Exception e) 
        {
            do {
                System.out.println("La opción ingresada no es valida, por favor intente nuevamente ingresando 0, 1 o 2");
                System.out.println("..............................................................");
                System.out.println("1.- Ejecutar Script");
                System.out.println("2.- Ejecutar Menu");
                System.out.println("0.- Salir");
                System.out.println("..............................................................");
                System.out.print("Opcion: ");
                opc = Integer.parseInt(scan.nextLine());  
            } while (opc!=0 && opc!=2 && opc != 1);
              
            
            
            
        }
        
        
        
        
        
        System.out.println();
        if(opc ==1)
        {
            System.out.println("Formateando el disco...");
            formato(numSectors,tamSectors);
            System.out.println("\nCreando un archivo llamado archivo1...");
            crear(512,"archivo1");
            System.out.println("\nCreando un archivo llamado archivo2...");
            crear(1024,"archivo2");
            System.out.println("\nCreando un archivo llamado archivo3...");
            crear(2048,"archivo3");           
            System.out.println("\nGenerando lista del directorio...");
            list();
            System.out.println("\nEliminando archivo2...");           
            remove("archivo2");
            System.out.println("\nGenerando lista del directorio nuevamente...");
            list();
            System.out.println("\nAbriendo archivo2...");
            printFile("archivo2");
            System.out.println("\nAbriendo archivo3...");
            printFile("archivo3");
            System.out.println("\nEscribiendo en un archivo...");
            //writeAt("archivo1",513,"ab");
            System.out.println("\nEscribiendo en un archivo...");
            //writeAt("archivo3",0,"ab");
            System.out.println("\nLeyendo un archivo...");
            //readAt("archivo3",0);
            System.out.println("\nImprimiendo un archivo...");
            //printFile("archivo1");
        }
        else if(opc == 2){
            int opc2 = 0;
            Scanner scan2 = new Scanner(System.in);
            
            do{
                System.out.println("-------- SISTEMA DE ARCHIVOS FULLHD 4K 1 LINK --------");
                System.out.println();
                System.out.println("1.- Formatear disco :");
                System.out.println("2.- Crear archivo :");
                System.out.println("3.- Borrar archivo :");
                System.out.println("4.- Leer archivo desde ruta :");
                System.out.println("5.- Escribir archivo desde ruta :");
                System.out.println("6.- Mostrar contenido de un archivo : ");
                System.out.println("7.- Mostrar entradas de directorio : ");
                System.out.println("0.- Salir :(");
                System.out.println();
                System.out.print("Opcion : ");
                try{
                    opc2 = Integer.parseInt(scan.nextLine());
                    if (opc2>=0 && opc2<=7) {
                        System.out.println("Ejecutando la Opción "+opc2+"..........");
                    }
                    else
                    {
                        System.out.println("Opción invalida");
                        
                    }
                }
                catch(Exception e ){
                    System.out.println("Opción invalida");
                    
                }
                String nombre, texto;
                int tamaño,pos;
                switch(opc2){
                    case 1:
                        System.out.println("\nFormatiando el disco...");
                        formato(numSectors, tamSectors);                        
                        break;
                    case 2:
                        System.out.println("\nCreando un archivo...");
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
                                    System.out.println("Por favor Ingrese Valores Positivos");
                                    System.out.println("....................................");
                                    System.out.println("Ingrese el tamaño");
                                    tamaño = Integer.parseInt(scan2.nextLine());
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            do {
                                System.out.println("Por favor Ingrese Valores Positivos");
                                System.out.println("....................................");
                                System.out.println("Ingrese el tamaño");
                                tamaño = Integer.parseInt(scan2.nextLine());
                                if (tamaño>0) {
                                    break;
                                }
                            } while (true);
                            
                        }
                        System.out.println("Ingrese el nombre");
                        nombre = scan2.nextLine();
                        crear(tamaño, nombre);
                        break;
                    case 3:
                        System.out.println("\nEliminando un archivo...");
                        System.out.println("Ingrese el nombre");
                        nombre = scan2.nextLine();
                        remove(nombre);
                        break;
                    case 4:
                        System.out.println("\nLeyendo un archivo...");
                        System.out.println("Ingrese el nombre");
                        nombre = scan2.nextLine();
                        System.out.println("Ingrese la posicion de inicio");
                        try{
                           pos = Integer.parseInt(scan2.nextLine());
                            if (pos>=0) 
                            {
                                System.out.println("Posición Ingresado ");
                            }
                            else
                            {
                                while(pos<0){
                                    System.out.println("Por favor Ingrese Valores Positivos");
                                    System.out.println("....................................");
                                    System.out.println("Ingrese La Posición");
                                    pos = Integer.parseInt(scan2.nextLine());
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            do {
                                System.out.println("Por favor Ingrese Valores Positivos");
                                System.out.println("....................................");
                                System.out.println("Ingrese la Posición");
                                pos = Integer.parseInt(scan2.nextLine());
                                if (pos>=0) {
                                    break;
                                }
                            } while (true);
                            
                        }
                        readAt(nombre, pos);                        
                        break;
                    case 5:
                        System.out.println("\nEscribiendo en un archivo...");
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
                                while(pos<0){
                                    System.out.println("Por favor Ingrese Valores Positivos");
                                    System.out.println("....................................");
                                    System.out.println("Ingrese La Posición");
                                    pos = Integer.parseInt(scan2.nextLine());
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            do {
                                System.out.println("Por favor Ingrese Valores Positivos");
                                System.out.println("....................................");
                                System.out.println("Ingrese la Posición");
                                pos = Integer.parseInt(scan2.nextLine());
                                if (pos>=0) {
                                    break;
                                }
                            } while (true);
                            
                        }
                        
                        System.out.println("Ingrese texto");
                        texto = (scan2.nextLine());
                        writeAt(nombre, pos, texto);                        
                        break;
                    case 6:
                        System.out.println("\nImprimiendo un archivo...");
                        System.out.println("Ingrese el nombre");
                        nombre = scan2.nextLine();
                        printFile(nombre);                        
                        break;
                    case 7:
                        System.out.println("\nGenerando lista del directorio...");
                        list();                        
                        break;
                }

            }while(opc2!=0);
        }
    }

    public static void cargarDisco(int numSectors, int tamSectors) throws FileNotFoundException, IOException{
        
        disco = new Disco(numSectors, tamSectors);     
        
        ArrayList<String> nombres = new ArrayList<String>();
        ArrayList<Integer> pos = new ArrayList<Integer>();
        ArrayList<Integer> espacioLibre = new ArrayList<Integer>();
                
        File f = new File( "Disco" );
        
        if(f.exists()){            
            BufferedReader entrada = new BufferedReader( new FileReader( f ) );
            String sector = entrada.readLine();
            
            
            if(compararVacio(sector)==false){//si el directorio no esta vacio obtiene la informacion
               
                
                //Buscamos todos los nombres y las posiciones del FCB de cada una para agregarla al directorio
                char[] linea = sector.toCharArray();
                int ini = 0;
                int fin = 0;
                boolean buscandoNombre = true;
                
                for (char c : linea) {
                    if (c!=' '){
                        if (c!='-'){
                            fin++;
                        }
                        else {
                            String text = sector.substring(ini, fin);
                            fin++;
                            ini = fin;
                            
                            if (buscandoNombre){
                                nombres.add(text);
                                buscandoNombre = false;
                            }
                            else {
                                pos.add(Integer.parseInt(text));
                                buscandoNombre = true;
                            }
                        }
                    }
                    else {
                        break;
                    }
                }
                
                directorio = new Directorio(pos, nombres);
                
                sector = entrada.readLine();

                for(int i = 0 ; i<sector.length(); i++ ){
                    espacioLibre.add(Integer.parseInt(sector.charAt(i)+""));
                }
                bitMap = new BitMap(espacioLibre);
                
            }
            else{
                formato(numSectors,tamSectors);
            }
        }
        else{
            formato(numSectors,tamSectors);
        }
    }
    
    public static void formato(int numSectors, int tamSectors){
        disco = new Disco(numSectors,tamSectors);
        disco.montarDisco();
        directorio = new Directorio();
        bitMap = new BitMap(disco);
        
        Sector org = new Sector(tamSectors);                
                
        char[] contenido = new char[tamSectors];
        contenido[0]='1';
        contenido[1]='1';
        
        for(int i = 2 ; i<tamSectors; i++){
            contenido[i]='0';
        }
        
        org.setContenido(contenido);
        disco.escribirSector(1, org);
    }

    public static boolean esUnico(String nombre){

        for(String name : directorio.getNombres()){
            if(name.equals(nombre)){
                return false;
            }
        }
        return true;
    }
    
    public static void crear(int tamaño, String nombre){

        if(nombre.length()<=8 && esUnico(nombre)){

            int totalSectores = 0;

            if(tamaño%512!=0){
                totalSectores = tamaño/512+2;
            }
            else{
                totalSectores = tamaño/512+1;
            }

            if(bitMap.hayEspacio(totalSectores)){
                //Hay espacio en el disco

                //Actualizo el bitMap en memoria
                int numSector = bitMap.getSectorDisponible();
                
                //Actualizo el bitMap en disco
                reescribirBitMap();

                //FCB nuevo
                FCB sectorFCB = new FCB(tamSectors,tamaño);
                
                //agrego el resto de los sectores para guardar los datos del archivo
                for(int i=0;i<(totalSectores-1); i++){
                    int numSectorI = bitMap.getSectorDisponible();
                    reescribirBitMap();
                    
                    Sector bn = new Sector(tamSectors,numSectorI);

                    
                    if (i==totalSectores-2){
                        bn = new Sector(tamSectors, (tamaño%tamSectors), numSectorI);
                    }

                    //AÑADIR EL PUNTERO DEL NUEVO BLOQUE AL FCB
                    sectorFCB.getSectors().add(bn);
                    sectorFCB.getPosicion().add(numSectorI);
                    
                    //REGISTRAR EL NUEVO BLOQUE EN EL DISCO
                    disco.escribirSector(numSectorI, bn);
                }
                
                //REGISTRAR EL FCB EN EL DISCO
                String salida = String.valueOf(sectorFCB.getTamañoArchivo());
                for (Sector bloque: sectorFCB.getSectors()){
                    salida+="-"+String.valueOf(bloque.getId());
                }
                
                Sector fc = new Sector(tamSectors,salida.toCharArray());
                disco.escribirSector(numSector, fc);
                
                //agrego al directorio
                directorio.getNombres().add(nombre);
                directorio.getPos().add(numSector);
                
                salida = "";
                boolean registrandoNombre = true;
                
                int total = directorio.getNombres().size();
                
                for (int i=0 ; i<total; i++){
                    if (registrandoNombre){
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
                System.out.println("Archivo creado exitosamente!!");
            }
            else
                System.out.println("No existe espacio para esta solicitud de tamaño");
        }
        else{
            
            if(nombre.length()>9)
                System.out.println("El tamaño del nombre sobrepasa los 8 caracteres");
            else
                System.out.println("El nombre del archivo ya existe en el directorio");
        }
    }
    
    public static void remove(String nombre){
        
        int i = 0;
        boolean flag = false;
        for(String name : directorio.getNombres()){
            if(name.equals(nombre)){
                //Encontre el archivo!!!

                //Borro la entrada en el directorio...
                directorio.getNombres().remove(i);
                int posFCB = directorio.getPos().get(i);
                directorio.getPos().remove(i);
                reescribirDirectorio();

                Sector sectorfcb = disco.leerSector(posFCB); //obtengo el FCB                    
                FCB fcb = convertirSectorToFCB(sectorfcb);

                for (Integer id: fcb.getPosicion()){
                    Sector bloqueNuevo = new Sector(tamSectors);
                    disco.escribirSector(id, bloqueNuevo);
                    bitMap.addSectorDisponible(id);
                    reescribirBitMap();
                }

                Sector bloqueFCB = new Sector(tamSectors);
                disco.escribirSector(posFCB, bloqueFCB);
                
                flag = true;
                break;
            }
            i++;
        }
        if(!flag){
            System.out.println("El archivo que intenta eliminar no existe!");
        }
    }
    
    public static void reescribirDirectorio(){
        
        String salida = "";
        boolean registrandoNombre = true;

        int total = directorio.getNombres().size();

        for (int i=0 ; i<total; i++){
            if (registrandoNombre){
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
    
    public static void readAt(String nombre, int posInicio){
        int i = 0;
        boolean flag = false;
        
        for(String name : directorio.getNombres()){
            if(name.equals(nombre)){
                //Encontre el archivo!!!
                
                int posFCB = directorio.getPos().get(i);
                Sector sectorfcb = disco.leerSector(posFCB); //obtengo el FCB                    


                FCB fcb = convertirSectorToFCB(sectorfcb);
                
                if (fcb.getTamañoArchivo()<posInicio){
                    System.out.println("Posicion Incorrecta");
                    break;
                }

                System.out.println("Contenido del archivo desde byte: " + posInicio);
                int contador = 1;
                int min = 0;
                int max = contador*tamSectors;
                
                for (Integer id: fcb.getPosicion()){
                    
                    Sector bloque = disco.leerSector(id);
                    String content = String.valueOf(bloque.getContenido());


                    //segmentado
                    if (posInicio<max && posInicio>=min){
                        int begin = posInicio-min;
                        imprimirSector(begin,content);
                    }

                    //completo
                    else {
                        if (posInicio<min){
                            System.out.println(content);
                        }
                        
                    }
                    min = max;
                    contador++;
                    max = contador*tamSectors;
                }
                flag = true;
                break;
            }
            i++;
        }
        if(!flag){
            System.out.println("El archivo que intenta eliminar no existe!");
        }
    }
  
    public static void writeAt(String nombre, int pos, String escribir){
        
        int i = 0;
        boolean flag = false;
        FCB fcb = new FCB(tamSectors);
        
        for(String name : directorio.getNombres()){
            if(name.equals(nombre)){
                //Encontre el archivo!!!
                
                int posFCB = directorio.getPos().get(i);
                Sector sectorfcb = disco.leerSector(posFCB); //obtengo el FCB                    

                fcb = convertirSectorToFCB(sectorfcb);
                
                int byteFinal = pos+escribir.length();
                
                if (fcb.getTamañoArchivo()<byteFinal){
                    System.out.println("No hay espacio suficiente para el texto ingresado");
                    break;
                }
                

                for (Integer id: fcb.getPosicion()){
                    
                    Sector bloque = disco.leerSector(id);
                    bloque.setId(id);
                    fcb.getSectors().add(bloque);
                    
                }
                flag = true;
                break;
            }
            i++;
        }
        
        
        
        int inicioEscribir = pos;
        int contadorSectors = 1;
        
        
        for (Sector c: fcb.getSectors()){
            
            if (inicioEscribir<contadorSectors*tamSectors){
                
             
                
                int disEscribir = (contadorSectors*tamSectors)-inicioEscribir;
                
                if (disEscribir>escribir.length()){
                    String corto = escribir;
                    String antiguo = String.valueOf(c.getContenido()).substring(escribir.length());
                    String nuevo = corto+antiguo;
                    
                    escribir = "";
                    
                    c.setContenido(nuevo.toCharArray());
                    disco.escribirSector(c.getId(),c);
                    inicioEscribir = contadorSectors*tamSectors;
                    
                }
                else {
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
            
            if (escribir.length()==0){
                break;
            }
            
        }
        
        if(!flag){
            System.out.println("El archivo que intenta escribir no existe!");
        }
    }
     
    public static void printFile(String nombre){
        
        int i = 0;
        boolean flag = false;
        
        for(String name : directorio.getNombres()){
            if(name.equals(nombre)){
                //Encontre el archivo!!!
                
                int posFCB = directorio.getPos().get(i);
                Sector sectorfcb = disco.leerSector(posFCB); //obtengo el FCB                    


                FCB fcb = convertirSectorToFCB(sectorfcb);

                System.out.println("Contenido del archivo: " + nombre);
                
                for (Integer id: fcb.getPosicion()){
                    Sector bloqueLeido = disco.leerSector(id);
                    System.out.println(String.valueOf(bloqueLeido.getContenido()));
                }
                flag = true;
                break;
            }
            i++;
        }
        if(!flag){
            System.out.println("El archivo que intenta eliminar no existe!");
        }
        
        
          
    }

    public static void list(){
        
        int i = 0;
        
        if(!directorio.getNombres().isEmpty()){
            System.out.println("Nombre"+"\t"+"Tamaño"+"\t\t"+"Sectors");
            for(String nombre : directorio.getNombres()){

                int posFCB = directorio.getPos().get(i); 
                Sector b = disco.leerSector(posFCB);
                FCB fcb = convertirSectorToFCB(b);
                
                System.out.println(nombre + "\t" + fcb.getTamañoArchivo() + " (bytes)" + "\t" + fcb.getPosicion().size());
                i++;
            }
            System.out.println("Directorio listado exitosamente!");
        }
        else
          System.out.println("El directorio esta vacio");
    }
    
    //Retorna true si la linea "sector" corresponde a una linea vacia.
    private static boolean compararVacio(String sector) {
        Sector vacio = new Sector(tamSectors);
        
        String sec = String.valueOf(vacio.getContenido());
        
        if (sec.equals(sector)){
            return true;
        }
        
        return false;
    }

    private static void reescribirBitMap() {
        
        String nuevo = "";
        
        for(int i=0; i<tamSectors; i++){
            nuevo+=String.valueOf(bitMap.getEspacios().get(i));
        }
        
        Sector org = new Sector(tamSectors);
        org.setContenido(nuevo.toCharArray());
        disco.escribirSector(1, org);  
    }

    private static FCB convertirSectorToFCB(Sector sectorfcb) {
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
                else {
                    if (buscandoTamaño){
                        tamaño = String.valueOf(linea).substring(inicio, fin);
                        salida.setTamañoArchivo(Integer.valueOf(tamaño));
                        fin++;
                        inicio=fin;
                        buscandoTamaño = false;
                    }
                    else {
                        temporal = String.valueOf(linea).substring(inicio, fin);
                        referencias.add(Integer.valueOf(temporal));
                        fin++;
                        inicio=fin;
                    }
                }
            }
            else {
                if (posicion==fin+1){
                    temporal = String.valueOf(linea).substring(inicio, fin);
                    referencias.add(Integer.valueOf(temporal));
                }
                break;
            }
            posicion++;
        }
        
        for (Integer referencia : referencias) {
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
