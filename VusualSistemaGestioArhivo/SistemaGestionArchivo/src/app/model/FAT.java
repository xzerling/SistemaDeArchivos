/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.util.ArrayList;

/**
 *
 * @author Zerling
 */
public class FAT 
{
    private ArrayList<Integer> tabla;
    private ArrayList<Integer> espacioActual;
    private int []arregloFat;
    private int posActual;
    
    public FAT(Disco disc)
    {
        this.espacioActual = new ArrayList<>();
        this.tabla = new ArrayList();
        this.arregloFat = new int[512];
        this.posActual = -1;
        this.espacioActual.add(1);
        this.espacioActual.add(1);
        for (int i = 0; i < disc.getNumSectores(); i++)
        {
            this.espacioActual.add(0);
            
        }
    }
    
    public FAT(ArrayList <Integer> libre)
    {
        this.espacioActual  = libre;
    }
    
    public boolean espacioDisponible(int sec)
    {
        int numSectores = 0;
        for (int i = 0; i < this.espacioActual.size(); i++)
        {
            if(this.espacioActual.get(i) == 0)
            {
                numSectores++;
            }
        }
        if(numSectores >= sec)
        {
            return true;
        }
        return false;
    }
    
    public ArrayList<Integer> getEspacioActual()
    {
        return this.espacioActual;
    }
    
    public void agregarEspacio(int indice)
    {
        this.espacioActual.set(indice, 0);
    }
    
    public int asignarSector()
    {
        for (int i = 0; i < this.espacioActual.size(); i++)
        {
            if(this.espacioActual.get(i) == 0)
            {
                this.espacioActual.set(i, 1);
                return i;
            }
            
        }
        return -1;
    }
    
    
}
