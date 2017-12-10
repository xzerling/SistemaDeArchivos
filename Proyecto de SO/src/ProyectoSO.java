import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import sistemaarchivos.BitMap;
import sistemaarchivos.Bloque;
import sistemaarchivos.Directorio;
import sistemaarchivos.Disco;
import sistemaarchivos.FCB;

public class ProyectoSO {

    /**
     * @param args the command line arguments
     */
    private static Directorio directorio;
    private static Disco disco;
    private static BitMap bitMap;
    private static int tamBloques;
    private static int numBloques;
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        int opc;
        
        numBloques = 1024;
        tamBloques = 512;
        
        Scanner scan = new Scanner(System.in);

        cargarDisco(numBloques,tamBloques);
        
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
            formato(numBloques,tamBloques);
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
                        formato(numBloques, tamBloques);                        
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

    public static void cargarDisco(int numBloques, int tamBloques) throws FileNotFoundException, IOException{
        
        disco = new Disco(numBloques, tamBloques);     
        
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
                formato(numBloques,tamBloques);
            }
        }
        else{
            formato(numBloques,tamBloques);
        }
    }
    
    public static void formato(int numBloques, int tamBloques){
        disco = new Disco(numBloques,tamBloques);
        disco.montarDisco();
        directorio = new Directorio();
        bitMap = new BitMap(disco);
        
        Bloque org = new Bloque(tamBloques);                
                
        char[] contenido = new char[tamBloques];
        contenido[0]='1';
        contenido[1]='1';
        
        for(int i = 2 ; i<tamBloques; i++){
            contenido[i]='0';
        }
        
        org.setContenido(contenido);
        disco.escribirBloque(1, org);
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
                FCB sectorFCB = new FCB(tamBloques,tamaño);
                
                //agrego el resto de los sectores para guardar los datos del archivo
                for(int i=0;i<(totalSectores-1); i++){
                    int numSectorI = bitMap.getSectorDisponible();
                    reescribirBitMap();
                    
                    Bloque bn = new Bloque(tamBloques,numSectorI);

                    
                    if (i==totalSectores-2){
                        bn = new Bloque(tamBloques, (tamaño%tamBloques), numSectorI);
                    }

                    //AÑADIR EL PUNTERO DEL NUEVO BLOQUE AL FCB
                    sectorFCB.getBloques().add(bn);
                    sectorFCB.getPosicion().add(numSectorI);
                    
                    //REGISTRAR EL NUEVO BLOQUE EN EL DISCO
                    disco.escribirBloque(numSectorI, bn);
                }
                
                //REGISTRAR EL FCB EN EL DISCO
                String salida = String.valueOf(sectorFCB.getTamañoArchivo());
                for (Bloque bloque: sectorFCB.getBloques()){
                    salida+="-"+String.valueOf(bloque.getId());
                }
                
                Bloque fc = new Bloque(tamBloques,salida.toCharArray());
                disco.escribirBloque(numSector, fc);
                
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
                
                
                
                Bloque dir = new Bloque(tamBloques,salida.toCharArray());
                
                disco.escribirBloque(0, dir);
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

                Bloque sectorfcb = disco.leerBloque(posFCB); //obtengo el FCB                    
                FCB fcb = convertirBloqueToFCB(sectorfcb);

                for (Integer id: fcb.getPosicion()){
                    Bloque bloqueNuevo = new Bloque(tamBloques);
                    disco.escribirBloque(id, bloqueNuevo);
                    bitMap.addSectorDisponible(id);
                    reescribirBitMap();
                }

                Bloque bloqueFCB = new Bloque(tamBloques);
                disco.escribirBloque(posFCB, bloqueFCB);
                
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
        Bloque dir = new Bloque(tamBloques,salida.toCharArray());

        disco.escribirBloque(0, dir);
    }
    
    public static void readAt(String nombre, int posInicio){
        int i = 0;
        boolean flag = false;
        
        for(String name : directorio.getNombres()){
            if(name.equals(nombre)){
                //Encontre el archivo!!!
                
                int posFCB = directorio.getPos().get(i);
                Bloque sectorfcb = disco.leerBloque(posFCB); //obtengo el FCB                    


                FCB fcb = convertirBloqueToFCB(sectorfcb);
                
                if (fcb.getTamañoArchivo()<posInicio){
                    System.out.println("Posicion Incorrecta");
                    break;
                }

                System.out.println("Contenido del archivo desde byte: " + posInicio);
                int contador = 1;
                int min = 0;
                int max = contador*tamBloques;
                
                for (Integer id: fcb.getPosicion()){
                    
                    Bloque bloque = disco.leerBloque(id);
                    String content = String.valueOf(bloque.getContenido());


                    //segmentado
                    if (posInicio<max && posInicio>=min){
                        int begin = posInicio-min;
                        imprimirBloque(begin,content);
                    }

                    //completo
                    else {
                        if (posInicio<min){
                            System.out.println(content);
                        }
                        
                    }
                    min = max;
                    contador++;
                    max = contador*tamBloques;
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
        FCB fcb = new FCB(tamBloques);
        
        for(String name : directorio.getNombres()){
            if(name.equals(nombre)){
                //Encontre el archivo!!!
                
                int posFCB = directorio.getPos().get(i);
                Bloque sectorfcb = disco.leerBloque(posFCB); //obtengo el FCB                    

                fcb = convertirBloqueToFCB(sectorfcb);
                
                int byteFinal = pos+escribir.length();
                
                if (fcb.getTamañoArchivo()<byteFinal){
                    System.out.println("No hay espacio suficiente para el texto ingresado");
                    break;
                }
                

                for (Integer id: fcb.getPosicion()){
                    
                    Bloque bloque = disco.leerBloque(id);
                    bloque.setId(id);
                    fcb.getBloques().add(bloque);
                    
                }
                flag = true;
                break;
            }
            i++;
        }
        
        
        
        int inicioEscribir = pos;
        int contadorBloques = 1;
        
        
        for (Bloque c: fcb.getBloques()){
            
            if (inicioEscribir<contadorBloques*tamBloques){
                
             
                
                int disEscribir = (contadorBloques*tamBloques)-inicioEscribir;
                
                if (disEscribir>escribir.length()){
                    String corto = escribir;
                    String antiguo = String.valueOf(c.getContenido()).substring(escribir.length());
                    String nuevo = corto+antiguo;
                    
                    escribir = "";
                    
                    c.setContenido(nuevo.toCharArray());
                    disco.escribirBloque(c.getId(),c);
                    inicioEscribir = contadorBloques*tamBloques;
                    
                }
                else {
                    String corto = escribir.substring(0, disEscribir);
                    String antiguo = String.valueOf(c.getContenido()).substring(0, inicioEscribir);
                    String nuevo = antiguo+corto;

                    escribir = escribir.substring(disEscribir);

                    c.setContenido(nuevo.toCharArray());
                    disco.escribirBloque(c.getId(),c);
                    inicioEscribir = contadorBloques*tamBloques;
                }
                contadorBloques++;
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
                Bloque sectorfcb = disco.leerBloque(posFCB); //obtengo el FCB                    


                FCB fcb = convertirBloqueToFCB(sectorfcb);

                System.out.println("Contenido del archivo: " + nombre);
                
                for (Integer id: fcb.getPosicion()){
                    Bloque bloqueLeido = disco.leerBloque(id);
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
            System.out.println("Nombre"+"\t"+"Tamaño"+"\t\t"+"Bloques");
            for(String nombre : directorio.getNombres()){

                int posFCB = directorio.getPos().get(i); 
                Bloque b = disco.leerBloque(posFCB);
                FCB fcb = convertirBloqueToFCB(b);
                
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
        Bloque vacio = new Bloque(tamBloques);
        
        String sec = String.valueOf(vacio.getContenido());
        
        if (sec.equals(sector)){
            return true;
        }
        
        return false;
    }

    private static void reescribirBitMap() {
        
        String nuevo = "";
        
        for(int i=0; i<tamBloques; i++){
            nuevo+=String.valueOf(bitMap.getEspacios().get(i));
        }
        
        Bloque org = new Bloque(tamBloques);
        org.setContenido(nuevo.toCharArray());
        disco.escribirBloque(1, org);  
    }

    private static FCB convertirBloqueToFCB(Bloque sectorfcb) {
        FCB salida = new FCB(tamBloques);
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

    private static void imprimirBloque(int value, String contenido) {
        char[] cont = contenido.toCharArray();
        
        for (int i = 0; i < cont.length; i++) {
            if (i>=value){
                System.out.print(cont[i]);
            }
        }
        System.out.print("\n");
    }
    
    
}
