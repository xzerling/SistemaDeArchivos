/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

/**
 *
 * @author Zerling
 */
public class Sector
{
    
    private char[] contenido;
    private int id;
    
    public Sector(int tamSector) 
    {
        this.id = -1;
        this.contenido = new char[tamSector];
        for (int i=0; i<tamSector; i++)
        {
            contenido[i]=' ';
        }
    }
    
    public Sector(int tamSector, char[] conten)
    {
        this.id = id;
        this.contenido = new char[tamSector];
        
        for (int i=0; i<conten.length; i++)
        {
            contenido[i]=conten[i];
        }
        
        for (int i=conten.length; i<tamSector; i++)
        {
            contenido[i]=' ';
        }
    }
    
    public Sector(int tamSector, int id)
    {
        this.id = id;
        this.contenido = new char[tamSector];
        
        for (int i=0; i<tamSector; i++)
        {
            contenido[i]='$';
        }
    }
    
    public Sector(int tamSector, int bytesOcupar, int id)
    {
        this.id = id;
        this.contenido = new char[tamSector];
        
        for (int i=0; i<bytesOcupar; i++)
        {
            contenido[i]='$';
        }
        for (int i=bytesOcupar; i<tamSector; i++)
        {
            contenido[i]=' ';
        }
    }

    public char[] getContenido()
    {
        return contenido;
    }

    public void setContenido(char[] contenido)
    {
        this.contenido = contenido;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    
}
