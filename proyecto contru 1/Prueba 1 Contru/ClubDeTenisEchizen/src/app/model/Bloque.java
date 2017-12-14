/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

/**
 *
 * @author Yorch
 */
public class Bloque
{
    private long bloque;
    private boolean estado;

    public Bloque(long bloque)
    {
        this.bloque = bloque;
        this.estado = true;
    }

    public long getBloque()
    {
        return bloque;
    }

    public void setBloque(long bloque)
    {
        this.bloque = bloque;
    }

    public boolean isEstado()
    {
        return estado;
    }

    public void setEstado(boolean estado)
    {
        this.estado = estado;
    }
    
    public String getEstado()
    {
        if(this.estado)
        {
            return "Disponible";
        }
        return "No disponible";
    }
    
    
    
    
}
