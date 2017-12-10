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
public class Cancha
{
    private ArrayList<Dia> dias;
    private String id;

    public Cancha(String id)
    {
        this.id = id;
        this.dias = new ArrayList<Dia>();
    }
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public boolean addDia(Dia e)
    {
        return dias.add(e);
    }
    
    /**
     * Busca un dia dado una fecha para saber los horarios disponibles de la cancha
     * @param fecha
     * @return día
     */
    public Dia getDia(String fecha)
    {
        int i;
        for (i = 0; i < this.dias.size(); i++)
        {
            if(this.dias.get(i).getFecha().equals(fecha))
            {
                return this.dias.get(i);
            }
        }
        return null;        
    }
    
}
