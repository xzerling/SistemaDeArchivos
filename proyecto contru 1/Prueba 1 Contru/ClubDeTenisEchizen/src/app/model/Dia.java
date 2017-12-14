/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.util.ArrayList;

/**
 *
 * @author Yorch
 */
public class Dia
{
    private String fecha;
    private String dia;
    private ArrayList<Bloque> bloques;

    public Dia(String fecha, String dia)
    {
        this.bloques = new ArrayList<Bloque>();
        this.aniadirBloques();
        this.dia = dia;
        this.fecha = fecha;
    }
    
    private void aniadirBloques()
    {
        for (int i = CommandNames.iniciolHoraDia; i < CommandNames.finalHoraDia; i++)
        {
            this.bloques.add(new Bloque(i));
        }
    }

    public String getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public String getDia()
    {
        return dia;
    }

    public void setDia(String dia)
    {
        this.dia = dia;
    }

    public ArrayList<Bloque> getBloques()
    {
        return bloques;
    }
    
    public Bloque getBloque(long bloque)
    {
        for(int i =0; i<this.bloques.size(); i++)
        {
            if(this.bloques.get(i).getBloque()==bloque)
            {
                return this.bloques.get(i);
            }
        }
        return null;
    }
   
}
