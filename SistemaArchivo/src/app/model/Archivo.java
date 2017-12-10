/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Yorch
 */
public class Archivo
{
    public String leerArchivo(String direccion)
    {
        String texto = "";
        try
        {
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead ;
            while((bfRead=bf.readLine())!=null)
            {
                temp = temp+bfRead;
            }
            
            texto = temp;
        }
        catch(Exception e)
        {
            System.out.println("No se encontro el archivo");
        }
        return texto;
    }
    
}
